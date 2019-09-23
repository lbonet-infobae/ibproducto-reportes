package com.infobae.ibproducto.reports.rest;

import java.time.LocalDate;
import java.time.ZoneId;
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
	public UsersReportWrapper getUsersReport() {
		LocalDate fromLocalDate = LocalDate.now().minusMonths(1);
		Date fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		return reportsService.getUsersReports(fromDate, new Date());
	}

}
