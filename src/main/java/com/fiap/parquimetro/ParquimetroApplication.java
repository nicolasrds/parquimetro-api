package com.fiap.parquimetro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ParquimetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParquimetroApplication.class, args);
	}

}
