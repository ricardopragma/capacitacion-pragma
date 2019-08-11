package com.familia.api.core;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ArchetypeMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchetypeMicroserviceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Core Rest service", "Api documentation of example for the familia archetype", "1.0",
				"Visit https://example.com/terms",
				new Contact("Ricardo Ayala", "www.pragma.com.co", "ricardo.ayala@pragma.com.co"), "License",
				"www.pragma.com.co/license", Collections.emptyList());
	}
}
