package com.toffee.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:${env:dev}.config.properties")
public class EmployeeServiceConfig {
	
	@Value("${url}")
	private String employeeServiceURL;
	
	@Value("${auid}")
	private String auid;
	
	public String getAuid() {
		return auid;
	}
	public String getEmployeeServiceURL() {
		return employeeServiceURL;
	}
}
