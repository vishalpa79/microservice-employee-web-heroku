package com.evoke.researchlabs.row.employee.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.evoke.researchlabs.row.employee.beans.Employee;

/**
 * 
 * RestFulClient implements the functionality like a postman client, it uses rest template object which
 * going to consume the service on respective URI and return the object in the form of string
 * @author P A VISHAL
 * @version 1.0
 * 
 */
@Component
@PropertySource(value = {"classpath:application.properties"})
public class RestServiceCall {
	@Autowired
	private Environment env;

	private static final Logger logger = LoggerFactory.getLogger(RestServiceCall.class);

	private String postUrl;

	/**
	 * function for consuming the employee services
	 * @param employeeBean
	 * @return
	 */
	public boolean postEntity(Employee employeeBean) {
		logger.info("Begin /POST request!");

		postUrl = env.getProperty("service.url");
		RestTemplate restTemplate = new RestTemplate();
		boolean status = true;
		try {
			String postResponse = restTemplate.postForObject(postUrl, employeeBean, String.class);
			logger.info("Response for Post Request: " + postResponse);
		} catch (Exception e) {
			status = false;
			logger.error("Exception while consuming rest service" + e);
		}

		return status;
	}

}
