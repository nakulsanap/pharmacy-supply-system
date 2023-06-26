package com.cognizant.medicalrepresentativeschedule.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GlobalErrorHandlerTest {

	@Autowired
	private GlobalErrorHandler globalErrorHandler;

	@Test
	public void testHandleAllErrors() {
		assertEquals(HttpStatus.BAD_REQUEST, globalErrorHandler.handleAllErrors(new Exception()).getStatusCode());
	}

	@Test
	public void testHandleFeignStatusException() {
		assertEquals(HttpStatus.FORBIDDEN, globalErrorHandler.handleTokenValidationFailedException(new TokenValidationFailedException()).getStatusCode());
	}

	@Test
	public void testHandleDateNotFoundException() {
		assertEquals(HttpStatus.NOT_FOUND, globalErrorHandler.handleDateNotFoundException(new InvalidDateException()).getStatusCode());
	}
}
