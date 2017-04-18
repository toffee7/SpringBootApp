package com.toffee.home.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.toffee.home.config.EmployeeServiceConfig;
import com.toffee.home.exception.EmployeeServiceException;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeServiceConfig employeeServiceConfig;

	@Override
	public String getEmployeeName(String soeid) throws EmployeeServiceException {
		
		if (soeid == null || soeid.isEmpty() || !soeid.matches("[a-zA-Z]{2}[0-9]{5}")) {
			throw new EmployeeServiceException(400,"Invalid SOEID");
		}
		// TODO - SEARCH FOR CLEANER IMPLEMENTATION
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) new URL(
					employeeServiceConfig.getEmployeeServiceURL() + soeid.toUpperCase() + "&ID" + employeeServiceConfig.getAuid())
							.openConnection();
			
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(con.getInputStream());

			XPathExpression xp = XPathFactory.newInstance().newXPath().compile("//response/people/Person/Name");

			return xp.evaluate(doc);

		} catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
}
