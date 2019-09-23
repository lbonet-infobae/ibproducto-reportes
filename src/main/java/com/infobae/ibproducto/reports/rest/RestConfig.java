package com.infobae.ibproducto.reports.rest;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import io.swagger.jaxrs.config.BeanConfig;

@Configuration
public class RestConfig extends ResourceConfig {

	static Logger logger = LoggerFactory.getLogger(RestConfig.class);
	
	public RestConfig() {
		
		logger.info("Registering rest APIs");
		
		register(ReportsResourceImpl.class);
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

	}
	
	@PostConstruct
	public void init() {
	
	 	BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("IB Producto Reportes API");
        beanConfig.setVersion("1.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/api");
        beanConfig.setHost("localhost:8091");

        beanConfig.setResourcePackage("com.infobae.ibproducto.reportes.api");
        beanConfig.setScan(true);
    
        logger.info("Restconfig swagger");
	}

}
