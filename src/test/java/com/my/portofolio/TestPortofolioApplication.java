package com.my.portofolio;

import org.springframework.boot.SpringApplication;

public class TestPortofolioApplication {

	public static void main(String[] args) {
		SpringApplication.from(PortofolioApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
