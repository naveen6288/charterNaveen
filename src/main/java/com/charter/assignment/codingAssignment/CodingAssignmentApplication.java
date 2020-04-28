package com.charter.assignment.codingAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.charter.assignment")
public class CodingAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingAssignmentApplication.class, args);
	}

}
