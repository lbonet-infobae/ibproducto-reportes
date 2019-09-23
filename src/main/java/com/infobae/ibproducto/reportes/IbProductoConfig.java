package com.infobae.ibproducto.reportes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IbProductoConfig {

	@Value("${infobae.ibproducto.config}/config")
	String configPath = null;
	
	@Bean
	public ConfigService createConfigService() {
		return new ConfigService(configPath);
	}
	
}
