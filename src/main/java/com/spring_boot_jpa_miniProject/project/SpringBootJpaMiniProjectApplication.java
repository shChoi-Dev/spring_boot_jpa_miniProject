package com.spring_boot_jpa_miniProject.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring_boot_jpa_miniProject.project"})
public class SpringBootJpaMiniProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaMiniProjectApplication.class, args);
	}

}
