package com.infobae.ibproducto.reports.dto;

import javax.xml.bind.annotation.XmlAttribute;

import io.swagger.annotations.ApiModelProperty;

public class AuthUser {

	@XmlAttribute
	@ApiModelProperty()
	String username;
	
	@XmlAttribute
	@ApiModelProperty()
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
