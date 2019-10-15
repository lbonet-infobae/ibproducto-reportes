package com.infobae.ibproducto.reports.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

import com.infobae.ibproducto.reports.api.AuthResource;

public class AuthResourceImpl implements AuthResource {

	@Override
	public Object validateLogin() {
		return new String("Success");
	}
	
}
