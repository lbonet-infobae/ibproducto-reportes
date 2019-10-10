package com.infobae.ibproducto.reports.rest;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.infobae.ibproducto.reports.api.ReportsResource;
import com.infobae.ibproducto.reports.dto.StoryCountReport;
import com.infobae.ibproducto.reports.dto.UsersReportWrapper;
import com.infobae.ibproducto.reports.service.ReportsService;

@Component
public class ReportsResourceImpl  implements ReportsResource{

	Logger logger = LoggerFactory.getLogger(ReportsResourceImpl.class);
	
	@Inject
	ReportsService reportsService;
	
	@Transactional(readOnly = true)
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
		
		return reportsService.getUsersReports(year, monthNumber);
	}
	
	@Override
	public StoryCountReport searchStoryCount(Integer year, Integer monthNumber) {

		if(year == null) {
			throw new RuntimeException("year cannot be null");
		}
		
		if(monthNumber == null) {
			throw new RuntimeException("month number cannot be null");
		}
		
		if(monthNumber <= 0 || monthNumber > 12) {
			throw new RuntimeException("month number is not valid");
		}
		
		return reportsService.getStoryCountReport(year, monthNumber);
	}
	
	@Override
	public void loadDataByYear(Integer year) {
		
		if(year == null) {
			throw new RuntimeException("year cannot be null");
		}
		
		reportsService.loadDataByYear(year);
		
	}

}
