package com.infobae.ibproducto.reports.rest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.infobae.ibproducto.reports.api.ReportsResource;
import com.infobae.ibproducto.reports.dto.UsersReportWrapper;
import com.infobae.ibproducto.reports.service.ReportsService;

@Component
public class ReportsResourceImpl  implements ReportsResource{

	Logger logger = LoggerFactory.getLogger(ReportsResourceImpl.class);
	
	@Inject
	ReportsService reportsService;
	
	@Cacheable("users-reports")
	@Override
	public UsersReportWrapper getUsersReport(Integer monthNumber) {
		
		if(monthNumber == null) {
			throw new RuntimeException("month number cannot be null");
		}
		
		if(monthNumber <= 0 || monthNumber > 12) {
			throw new RuntimeException("month number is not valid");
		}
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		LocalDate fromLocalDate = LocalDate.of(year, monthNumber, 1);
		Date fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		LocalDate toLocalDate = fromLocalDate.with(TemporalAdjusters.lastDayOfMonth());
		Date toDate = Date.from(toLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		return reportsService.getUsersReports(fromDate, toDate);
	}

}
