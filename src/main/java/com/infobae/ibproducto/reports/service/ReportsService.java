package com.infobae.ibproducto.reports.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.infobae.ibproducto.reports.dto.UserReportDto;
import com.infobae.ibproducto.reports.dto.UsersReportWrapper;
import com.infobae.ibproducto.reports.websked.dto.StoryCount;
import com.infobae.ibproducto.reports.websked.dto.User;

@Service
public class ReportsService {
	
	@Inject
	WebskedService webskedService;

	public UsersReportWrapper getUsersReports(Date from, Date to) {
		
		if(from == null || to == null) {
			throw new RuntimeException("from date and to date cannot be null");
		}
		
		List<UserReportDto> usersReport = new ArrayList<>();
		
		List<User> users = webskedService.getUsers();
				
		for(User user : users) {
			
			UserReportDto userReport = new UserReportDto();
			
			StoryCount storyCount = webskedService.getStoryCount(user.getName(), from, to);
			
			userReport.setName(user.getName().trim());
			userReport.setUsername(user.getUsername().trim());
			userReport.setStoryCount((storyCount.getCounts() != null) ? storyCount.getCounts().getTotal() : 0);
			
			usersReport.add(userReport);
		}
		
		// comparator for name sort
		Comparator<UserReportDto> nameComparator = (UserReportDto u1, UserReportDto u2) -> u1.getName().compareTo(u2.getName());
		// sorting by name
		Collections.sort(usersReport, nameComparator);
				
		return new UsersReportWrapper(usersReport, users.size());
	}
	
}
