package com.cognizant.medicalrepresentativeschedule.service.impl;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.model.JwtResponse;
import com.cognizant.medicalrepresentativeschedule.service.SessionValidationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionValidationServiceImplTest {

	@MockBean
	private AuthenticationFeignClient authenticationFeignClient;

	@Autowired
	private SessionValidationService sessionValidationService;
//
//	@Test
//	public void testIsValidSession_Valid() throws TokenValidationFailedException {
//
//		when(authenticationFeignClient.verifyToken("token")).thenReturn(new JwtResponse("root", "root", true));
//		Boolean isValid = sessionValidationService.isValidSession("token");
//
//		assertTrue(isValid);
//
//	}

	@Test(expected = Exception.class)
	public void testIsValidSession_NotValid() throws Exception {

		when(authenticationFeignClient.verifyToken("token")).thenReturn(new JwtResponse("root", "root", true));
		sessionValidationService.isValidSession("token");

	}

}
