package com.infobae.ibproducto.reports.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.infobae.ibproducto.reports.dto.SearchFilter;
import com.infobae.ibproducto.reports.dto.StoryCountReport;
import com.infobae.ibproducto.reports.dto.UsersReportWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("reports")
@Api("/reports")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public interface ReportsResource {
	
	@GET
	@Path("{year}/{month}/users/all") @Produces("application/json")
	@ApiOperation(value="Returns report for all users", response=UsersReportWrapper.class)
	public UsersReportWrapper getMonthlyUsersReport(
			@PathParam(value="year") @ApiParam(required=true, value="year number") Integer yearNumber,
			@PathParam(value="month") @ApiParam(required=true, value="month number") Integer monthNumber);
	
	@POST
	@Path("search/storycount") @Produces("application/json")
	@ApiOperation(value="Returns report for all users", response=StoryCountReport.class)
	public StoryCountReport searchStoryCount(SearchFilter searchFilter);
	
	
}
