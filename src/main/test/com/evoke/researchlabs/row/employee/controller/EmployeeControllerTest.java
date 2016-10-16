package com.evoke.researchlabs.row.employee.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.evoke.researchlabs.row.employee.beans.Employee;
import com.evoke.researchlabs.row.employee.util.RestServiceCall;

/**
 * @author apandiri
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController = new EmployeeController();

	@Mock
	private RestServiceCall restfulclient;
	@Mock
	private HttpServletRequest request;

	@Mock
	private BindingResult result;

	@Mock
	public Employee employee;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	/**
	 * Test method for
	 * {@link com.evoke.researchlabs.row.employee.controller.EmployeeController#executeLogin(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.evoke.researchlabs.row.employee.beans.Employee, org.springframework.validation.BindingResult)}
	 * .
	 */
	@Test
	public void testExecuteLogin_success() {
		when(restfulclient.postEntity(employee)).thenReturn(true);
		employeeController.executeLogin(request, employee, result);
		verify(restfulclient).postEntity(employee);
		verifyNoMoreInteractions(restfulclient);

	}

	@Test
	public void testExecuteLogin_failure() {
		when(restfulclient.postEntity(employee)).thenReturn(false);
		employeeController.executeLogin(request, employee, result);
		verify(restfulclient).postEntity(employee);
		verifyNoMoreInteractions(restfulclient);
	}

}
