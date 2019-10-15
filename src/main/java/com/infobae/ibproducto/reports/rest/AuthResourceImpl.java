package com.infobae.ibproducto.reports.rest;

import com.infobae.ibproducto.reports.api.AuthResource;

public class AuthResourceImpl implements AuthResource {

	@Override
	public Object validateLogin() {
		return new String("Success");
	}
	
}
