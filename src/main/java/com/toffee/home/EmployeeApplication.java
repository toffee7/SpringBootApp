package com.toffee.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.toffee.home.controller.EmployeeController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * @author st50102
 * 
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackageClasses = { EmployeeController.class }, basePackages = { "com.toffee.home" })
public class EmployeeApplication {
	public static void main(String... args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.toffee.home")).paths(PathSelectors.ant("/employee-service/api/v1/**"))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Employee Service API").description("Employee Service").termsOfServiceUrl("")
				.license("").version("1.0").build();
	}

}
