package com.infobae.ibproducto.reports.secure;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infobae.ibproducto.reports.dao.AuthUserDao;
import com.infobae.ibproducto.reports.model.AuthUserDb;

@Service
public class UserPrincipalDetailsService implements UserDetailsService{

	@Inject
	AuthUserDao authUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AuthUserDb user = this.authUserDao.findByUsername(username).orElseThrow(() -> new RuntimeException("User " + username + " not found."));
		UserPrincipal userPrincipal = new UserPrincipal(user);
		
		return userPrincipal;
	}

}
