package com.infobae.ibproducto.reports.schedule;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.infobae.ibproducto.reports.service.ReportsService;

@Component
public class Scheduler {
	
	Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Inject
	ReportsService reportsService;
	
	@Value("${websked.config.reports.cron.enabled}")
	boolean initDataEnabled;
	
	@Scheduled(cron = "${websked.config.reports.cron.value}")
	public void updateReports() {
		
		if(!initDataEnabled) {
			return;
		}
		
		reportsService.loadCurrentMonthData();
		
	}
	
	
}
