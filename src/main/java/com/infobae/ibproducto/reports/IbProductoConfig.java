package com.infobae.ibproducto.reports;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.infobae.ibproducto.reports.dao.AuthUserDao;
import com.infobae.ibproducto.reports.model.AuthUserDb;

@Configuration
public class IbProductoConfig {
	
	Logger logger = LoggerFactory.getLogger(IbProductoConfig.class);
	
	@Inject
	PasswordEncoder passwordEncoder;
	
	@Inject
	AuthUserDao authUserDao;

	@Value("${infobae.ibproducto.config}/config")
	String configPath = null;
	
	@Bean
	public ConfigService createConfigService() {
		return new ConfigService(configPath);
	}
	
	@PostConstruct
    public void init() {
        
		Optional<AuthUserDb> adminUser = authUserDao.findByUsername("admin");
		
		if(!adminUser.isPresent()) {
			AuthUserDb newAdmin = new AuthUserDb("admin", passwordEncoder.encode("infobaeadmin"), true, "ADMIN");
			
			authUserDao.save(newAdmin);
		}
		
    }
	
}
