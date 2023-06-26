package com.cognizant.medicalrepresentativeschedule.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
public class ErrorResponseTest {

	private ErrorResponse errorResponse;

	@Before
	public void setup() {
		log.info("Start");

		errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		errorResponse.setReason("Bad request");
		errorResponse.setMessage("Please enter valid date");

		log.info("End");
	}

	@Test
	public void testNoArgsConstructor() {
		log.info("Start");

		errorResponse = new ErrorResponse();
		assertEquals(null, errorResponse.getStatus());
		assertEquals(null, errorResponse.getReason());
		assertEquals(null, errorResponse.getMessage());

		log.info("End");
	}

	@Test
	public void testAllArgsConstructor() {
		log.info("Start");

		errorResponse = new ErrorResponse("Please enter valid date", "Bad request", HttpStatus.BAD_REQUEST, null);
		assertEquals("Bad request", errorResponse.getReason());

		log.info("End");
	}

	@Test
	public void testGetters() throws Exception {
		log.info("Start");

		assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatus());
		assertEquals("Bad request", errorResponse.getReason());
		assertEquals("Please enter valid date", errorResponse.getMessage());

		log.info("End");
	}

	@Test
	public void testSetter() {
		log.info("Start");

		errorResponse.setMessage("Hello");
		errorResponse.setReason("Hello");
		errorResponse.setStatus(HttpStatus.OK);
		errorResponse.setTimestamp(LocalDateTime.now());
		assertEquals("Hello", errorResponse.getMessage());

		log.info("End");
	}

	@Test
	public void testEquals() {
		log.info("Start");

		boolean equals = errorResponse.equals(errorResponse);
		assertTrue(equals);

		log.info("End");
	}

	@Test
	public void testHashCode() {
		log.info("Start");

		int hashCode = errorResponse.hashCode();
		assertEquals(hashCode, errorResponse.hashCode());

		log.info("End");
	}

	@Test
	public void testToString() {
		log.info("Start");

		assertEquals(
				"ErrorResponse(message=" + errorResponse.getMessage() + ", reason=" + errorResponse.getReason()
						+ ", status=" + errorResponse.getStatus() + ", timestamp=" + errorResponse.getTimestamp() + ")",
				errorResponse.toString());

		log.info("End");
	}
}
