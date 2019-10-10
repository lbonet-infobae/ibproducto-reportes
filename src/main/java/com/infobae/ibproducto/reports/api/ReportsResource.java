package com.infobae.ibproducto.reports.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
	@Path("/storycount/{year}/{month}") @Produces("application/json")
	@ApiOperation(value="Returns report for all users", response=StoryCountReport.class)
	public StoryCountReport searchStoryCount(
			@PathParam(value="year") @ApiParam(required=true, value="year number") Integer yearNumber,
			@PathParam(value="month") @ApiParam(required=true, value="month number") Integer monthNumber
		);
	
	@GET
	@Path("/storycount/{year}/{month}/users") @Produces("application/json")
	@ApiOperation(value="Returns report for all users", response=UsersReportWrapper.class)
	public UsersReportWrapper getMonthlyUsersReport(
			@PathParam(value="year") @ApiParam(required=true, value="year number") Integer yearNumber,
			@PathParam(value="month") @ApiParam(required=true, value="month number") Integer monthNumber
		);
	
	@POST
	@Path("load/{year}") @Produces("application/json")
	@ApiOperation(value="Load report data", response=StoryCountReport.class)
	public void loadDataByYear(
			@PathParam(value="year") @ApiParam(required=true, value="year number") Integer year
		);
	
	
}
