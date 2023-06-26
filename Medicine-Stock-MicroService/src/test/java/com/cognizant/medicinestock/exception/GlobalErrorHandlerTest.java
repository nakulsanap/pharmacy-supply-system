package com.cognizant.medicinestock.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GlobalErrorHandlerTest {
	
	@Autowired
	private GlobalErrorHandler globalErrorHandler;
	
	@Test
	public void testAllExceptions() {
		log.info("START");
		assertEquals(HttpStatus.NOT_FOUND,globalErrorHandler.allExceptions(new Exception()).getStatusCode());
		log.info("END");
	}
	
	@Test
	public void testHandleTokenValidationFailedException() {
		log.info("START");
		assertEquals(HttpStatus.FORBIDDEN,globalErrorHandler.handleTokenValidationFailedException(new TokenValidationFailedException()).getStatusCode());
		log.info("END");
	}
}
