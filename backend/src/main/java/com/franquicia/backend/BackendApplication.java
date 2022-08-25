package com.franquicia.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class BackendApplication {
	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("ARG"));
		System.out.println("Estoy en el init");
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
