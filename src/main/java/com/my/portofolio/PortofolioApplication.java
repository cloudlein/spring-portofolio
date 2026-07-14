package com.my.portofolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springdoc.core.configuration.SpringDocDataRestConfiguration.class,
		org.springdoc.core.configuration.SpringDocConfiguration.class
})
public class PortofolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortofolioApplication.class, args);
	}

}
