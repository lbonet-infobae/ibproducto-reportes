package com.infobae.ibproducto.reports.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("auth")
@Api("/auth")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public interface AuthResource {

	@GET
	@Path("validate") @Produces("application/json")
	@ApiOperation(value="Load report data", response=Object.class)
	public Object validateLogin();
	
	
}
