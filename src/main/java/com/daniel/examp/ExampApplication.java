package com.daniel.examp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.daniel.examp.controllers","com.daniel.examp.general.processes","com.daniel.examp.entities"})
public class ExampApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExampApplication.class, args);
	}
}