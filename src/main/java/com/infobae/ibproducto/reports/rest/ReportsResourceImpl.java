package com.infobae.ibproducto.reports.rest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.infobae.ibproducto.reports.api.ReportsResource;
import com.infobae.ibproducto.reports.dto.SearchFilter;
import com.infobae.ibproducto.reports.dto.StoryCountReport;
import com.infobae.ibproducto.reports.dto.UsersReportWrapper;
import com.infobae.ibproducto.reports.service.ReportsService;

@Component
public class ReportsResourceImpl  implements ReportsResource{

	Logger logger = LoggerFactory.getLogger(ReportsResourceImpl.class);
	
	@Inject
	ReportsService reportsService;
	
	@Cacheable("users-reports")
	@Override
	public UsersReportWrapper getMonthlyUsersReport(Integer year, Integer monthNumber) {
		
		if(year == null) {
			throw new RuntimeException("year cannot be null");
		}
		
		if(monthNumber == null) {
			throw new RuntimeException("month number cannot be null");
		}
		
		if(monthNumber <= 0 || monthNumber > 12) {
			throw new RuntimeException("month number is not valid");
		}
		
		LocalDate fromLocalDate = LocalDate.of(year, monthNumber, 1);
		Date fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		LocalDate toLocalDate = fromLocalDate.with(TemporalAdjusters.lastDayOfMonth());
		Date toDate = Date.from(toLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		return reportsService.getUsersReports(fromDate, toDate);
	}
	
	@Override
	public StoryCountReport searchStoryCount(SearchFilter searchFilter) {

		if(searchFilter.getFrom() == null || searchFilter.getTo() == null) {
			throw new RuntimeException("dates 'from' and 'to' cannot be null");
		}
		
		if(searchFilter.getFrom().after(searchFilter.getTo())) {
			throw new RuntimeException("date 'from' must be before date 'to'");
		}
		
		return reportsService.getStoryCountReport(searchFilter.getFrom(), searchFilter.getTo());
	}

}
