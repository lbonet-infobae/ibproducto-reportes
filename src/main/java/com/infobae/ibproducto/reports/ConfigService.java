package com.infobae.ibproducto.reports;

import java.io.File;

public class ConfigService {
	
	String configPath;

	public ConfigService(String configPath) {
		this.configPath = configPath;
	}

	public File getConfigFile(String name) {
		return new File(configPath, name);
	}

	
}
