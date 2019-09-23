package com.infobae.ibproducto.reportes.secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug=false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		logger.info("Configuring Application Security");
		
		super.configure(http);
		
		http.headers().frameOptions().disable();
		
		http
    	.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/economia/cotizacion/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/swagger.json").permitAll()
            .anyRequest().permitAll();

	}
	
	
}
