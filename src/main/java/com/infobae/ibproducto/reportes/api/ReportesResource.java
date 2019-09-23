package com.infobae.ibproducto.reportes.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;

@Path("reportes")
@Api("/reportes")
@Consumes({"application/xml", "application/json"})
@Produces({"application/xml", "application/json"})
public interface ReportesResource {
	
	
}
