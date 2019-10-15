package com.infobae.ibproducto.reports.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="auth_user")
public class AuthUserDb {

	private Long id;
	
	private String username;
	
	private String password;
	
	private boolean active;
	
	private String roles = "";

	public AuthUserDb(String username, String password, boolean active, String roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.active = true;
	}
	
	protected AuthUserDb() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="username", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="active")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name="roles")
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	@Transient
	public List<String> getRolesList(){
		if(this.roles != null && this.roles != "") {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<String>();
	}
	
}
