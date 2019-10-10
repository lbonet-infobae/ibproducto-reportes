package com.infobae.ibproducto.reports.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.infobae.ibproducto.reports.dao.ReportDao;
import com.infobae.ibproducto.reports.dao.UserDao;
import com.infobae.ibproducto.reports.dto.StoryCountReport;
import com.infobae.ibproducto.reports.dto.UserReportDto;
import com.infobae.ibproducto.reports.dto.UsersReportWrapper;
import com.infobae.ibproducto.reports.model.ReportDb;
import com.infobae.ibproducto.reports.model.UserDb;
import com.infobae.ibproducto.reports.websked.dto.MedianDeadlineMiss;
import com.infobae.ibproducto.reports.websked.dto.StoryCount;

@Service
public class ReportsService {
	
	@Inject
	WebskedService webskedService;
	
	@Inject
	UserDao userDao;
	
	@Inject
	ReportDao reportDao;

	public UsersReportWrapper getUsersReports(Integer year, Integer month) {
		
		if(year == null || month == null) {
			throw new RuntimeException("year or month cannot be null");
		}
		
		List<UserReportDto> usersReports = reportDao.findUsersReportsByYearAndMonth(year, month);
				
		return new UsersReportWrapper(usersReports, usersReports.size());
	}
	
	public StoryCountReport getStoryCountReport(Integer year, Integer month) {
		
		if(year == null || month == null) {
			throw new RuntimeException("from date and to date cannot be null");
		}
		
		StoryCountReport storyCountReport = reportDao.findStoryCountByYearAndMonth(year, month).orElse(new StoryCountReport());
		
		return storyCountReport;
	}
	
	public void loadDataByYear(int year) {
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		
		if(year == currentYear && currentMonth == 1) {
			return;
		}
		
		int limit = (year == currentYear) ? currentMonth - 1 : 12;
		
		webskedService.getUsers().stream().filter(u -> u.getUsername().contains("infobae")).forEach(u -> {
			
			UserDb user = userDao.findByName(u.getName()).orElseGet(() -> {
				UserDb newUser = new UserDb(u.getName(), u.getUsername());
				return userDao.save(newUser);
			});
			
			for(int i = 1; i <= limit; i++) {
				LocalDate fromLocalDate = LocalDate.of(year, i, 1);
				Date fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneOffset.UTC).toInstant());
				
				LocalDate toLocalDate = fromLocalDate.with(TemporalAdjusters.lastDayOfMonth());
				Date toDate = Date.from(toLocalDate.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant());
				
				Optional<ReportDb> bdReport = reportDao.findByUserAndMonthAndYear(user, i, year);
				
				ReportDb report;
				if(!bdReport.isPresent()) {
					report = new ReportDb();
					report.setUser(user);
					report.setYear(year);
					report.setMonth(i);
				} else {
					report = bdReport.get();
				}
				
				StoryCount storyCount = webskedService.getStoryCount(user.getName(), fromDate, toDate);
				
				MedianDeadlineMiss medianDeadlineMiss = webskedService.getMedianDeadlineMiss(user.getName(), fromDate, toDate);
				
				report.setStoryCount((storyCount.getCounts() != null) ? storyCount.getCounts().getTotal() : 0);
				report.setMedianDeadlineMiss(medianDeadlineMiss.getResult());
				
				reportDao.save(report);
			}
			
		});
	}
	
	public void loadCurrentMonthData() {
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		
		webskedService.getUsers().stream().filter(u -> u.getUsername().contains("infobae")).forEach(u -> {
			
			UserDb user = userDao.findByName(u.getName()).orElseGet(() -> {
				UserDb newUser = new UserDb(u.getName(), u.getUsername());
				return userDao.save(newUser);
			});
			
			LocalDate fromLocalDate = LocalDate.of(currentYear, currentMonth, 1);
			Date fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneOffset.UTC).toInstant());
			
			Date toDate = Date.from(LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant());
			
			Optional<ReportDb> bdReport = reportDao.findByUserAndMonthAndYear(user, currentMonth, currentYear);
			
			ReportDb report;
			if(!bdReport.isPresent()) {
				report = new ReportDb();
				report.setUser(user);
				report.setYear(currentYear);
				report.setMonth(currentMonth);
			} else {
				report = bdReport.get();
			}
			
			StoryCount storyCount = webskedService.getStoryCount(user.getName(), fromDate, toDate);
			
			MedianDeadlineMiss medianDeadlineMiss = webskedService.getMedianDeadlineMiss(user.getName(), fromDate, toDate);
			
			report.setStoryCount((storyCount.getCounts() != null) ? storyCount.getCounts().getTotal() : 0);
			report.setMedianDeadlineMiss(medianDeadlineMiss.getResult());
			
			reportDao.save(report);
			
		});
	}
}
