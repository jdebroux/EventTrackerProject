package com.skilldistillery.weddings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.skilldistillery.weddings")
@EntityScan("com.skilldistillery.weddings")
public class WeddingTrackerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WeddingTrackerApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WeddingTrackerApplication.class, args);
	}

}
