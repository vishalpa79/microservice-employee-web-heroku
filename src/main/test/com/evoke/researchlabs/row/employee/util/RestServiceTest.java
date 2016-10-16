package com.evoke.researchlabs.row.employee.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.evoke.researchlabs.row.employee.beans.Employee;
import com.evoke.researchlabs.row.employee.util.RestServiceCall;

/**
 * @author apandiri
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(RestServiceCall.class)
public class RestServiceTest {

	private String SERVICE_URL = "http://127.0.0.1:8080/employee";
	@InjectMocks
	private RestServiceCall restfulclient;
	@Mock
	RestTemplate resttemplate;
	@Mock
	public Employee employee;
	@Mock
	private Environment env;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public final void testPostEntity_success() throws Exception {

		PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(resttemplate);
		when(env.getProperty("service.url")).thenReturn(SERVICE_URL);
		when(resttemplate.postForObject(SERVICE_URL, employee, String.class)).thenReturn("abc");
		assertEquals(true, restfulclient.postEntity(employee));
		verify(resttemplate).postForObject(SERVICE_URL, employee, String.class);

	}

	@Test
	public final void testPostEntity_failure() throws Exception {
		PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(resttemplate);
		when(resttemplate.postForObject(SERVICE_URL, employee, String.class))
				.thenThrow(new RestClientException("application exception"));
		when(env.getProperty("service.url")).thenReturn(SERVICE_URL);
		assertEquals(false, restfulclient.postEntity(employee));
		verify(resttemplate).postForObject(SERVICE_URL, employee, String.class);

	}

}
