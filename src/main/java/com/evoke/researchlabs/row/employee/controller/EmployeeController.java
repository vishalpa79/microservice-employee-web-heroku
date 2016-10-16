package com.evoke.researchlabs.row.employee.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.evoke.researchlabs.row.employee.beans.Employee;
import com.evoke.researchlabs.row.employee.exception.ApplicationException;
import com.evoke.researchlabs.row.employee.util.RestServiceCall;

/**
 * EmployeeController class defined the implementation of two methods formLogin() accept get request and executeLogin() accept post request
 * @author apandiri
 * @version 1.0
 * 
 */

@Controller
public class EmployeeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired(required = true)
	RestServiceCall restfulClient;

	/**
	 * adding an object of EmployeeBean into a model attribute employeeBean
	 * @return login view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView formLogin() {
		return new ModelAndView("login", "employeeBean", new Employee());
	}

	/**
	 * the method going to implement the functionality of invoking methods for consuming employee services
	 * @param request
	 * @param response
	 * @param employeeBean
	 * @param result
	 * @return ModelAndView return the desire view name based on the execution flow
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, @ModelAttribute("employeeBean") Employee employeeBean, BindingResult result) {
		logger.info("exectueLogin:start");
		ModelAndView model = null;
		try {
			boolean userData = restfulClient.postEntity(employeeBean);
			if (userData) {
				logger.info("user data saved successfully");
				request.setAttribute("userName", employeeBean.getUserName());
				model = new ModelAndView("welcome");

			} else {
				logger.info("user data not saved");
				request.setAttribute("message", "Message not stored in Mongodb");
				model = new ModelAndView("login");
				model.addObject("employeeBean", employeeBean);
				throw new ApplicationException("Exception while consuming service");
			}

		} catch (ApplicationException e) {
			logger.error("Exception while consuming employee services" + e);

		}
		return model;

	}

}
