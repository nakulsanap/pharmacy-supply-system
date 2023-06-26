package com.cognizant.authorization.exception;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.authorization.exception.GlobalErrorHandler;

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
		assertEquals(HttpStatus.NOT_FOUND, globalErrorHandler.handleAllMedicineStockErrors(new Exception()).getStatusCode());
		log.info("END");
	}

	@Test
	public void testHandleTokenValidationFailedException() {
		log.info("START");
		assertEquals(HttpStatus.FORBIDDEN,
				globalErrorHandler
						.handleInternalAuthenticationServiceException(new InternalAuthenticationServiceException(null))
						.getStatusCode());
		log.info("END");
	}
}