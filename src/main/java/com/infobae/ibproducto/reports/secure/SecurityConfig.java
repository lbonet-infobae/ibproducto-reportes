package com.infobae.ibproducto.reports.secure;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Inject
	UserPrincipalDetailsService userPrincipalDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		logger.info("Configuring Application Security");
		
		super.configure(http);
		
		http.headers().frameOptions().disable();
		
		http
    	.csrf().disable()
//	    	.exceptionHandling().authenticationEntryPoint(
//	    			(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//	    	.and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/reports/**").authenticated()
            .antMatchers(HttpMethod.GET, "/api/auth/**").authenticated()
            .antMatchers(HttpMethod.GET, "/api/swagger.json").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and().formLogin().disable();

	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
