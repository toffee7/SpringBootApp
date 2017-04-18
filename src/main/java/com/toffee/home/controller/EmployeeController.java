package com.toffee.home.controller;

import static com.toffee.home.utils.Constants.API_VERSION_URL;
import static com.toffee.home.utils.Constants.EMPLOYEE_SERVICE_URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toffee.home.service.EmployeeServiceImpl;

@RestController
@RequestMapping(EMPLOYEE_SERVICE_URL + API_VERSION_URL)
public class EmployeeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;

	@CrossOrigin
	@RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.GET)
	public String getEmployeeName(@PathVariable String employeeID) {
		LOG.info("Querying Global directory for SOEID: {}", employeeID);
		return employeeServiceImpl.getEmployeeName(employeeID);

	}
}
