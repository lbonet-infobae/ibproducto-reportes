package com.infobae.ibproducto.reports.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.infobae.ibproducto.reports.dto.UsersReportWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("reports")
@Api("/reports")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public interface ReportsResource {
	
	@GET
	@Path("/users/all") @Produces("application/json")
	@ApiOperation(value="Returns report for all users", response=UsersReportWrapper.class)
	public UsersReportWrapper getUsersReport();
	
	
}
