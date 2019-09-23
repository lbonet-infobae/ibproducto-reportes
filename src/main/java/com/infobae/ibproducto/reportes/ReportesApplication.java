package com.infobae.ibproducto.reportes;

import java.util.Properties;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication(scanBasePackages={"com.infobae.ibproducto.reportes"})
@EnableCaching
public class ReportesApplication {

	@Inject
	private Environment environment;
	
	private static final Logger logger = LoggerFactory.getLogger(ReportesApplication.class);
	
	public static void main(String[] args) {
		logger.info("Starting IB Producto");
		
		new SpringApplicationBuilder(ReportesApplication.class)
			.sources(ReportesApplication.class)
			.bannerMode(Banner.Mode.OFF)
			.properties(getProperties())
			.run(args);
	}
	
	static Properties getProperties() {
		Properties props = new Properties();

		props.put("infobae.ibproducto.config", System.getProperty("infobae.ibproducto.config", "."));
  
		props.put("spring.config.location", System.getProperty("infobae.ibproducto.config", ".") + "/config/");
		props.put("spring.config.name", "ibproducto-reportes");
  
		logger.warn("infobae.ibproducto.config: " + System.getProperty("infobae.ibproducto.config", "."));
  
		logger.warn("spring.config.location: " + props.get("infobae.ibproducto.config"));
      
      return props;
   }

}
