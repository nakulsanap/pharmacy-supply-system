
package com.cognizant.authorization.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserNotFoundExceptionTest {

	@Mock
	private UserNotFoundException userNotFoundException;

	@Test
	public void testOneArgConstructor() {
		log.info("Start");
		
		userNotFoundException = new UserNotFoundException("User Not Found.");
		assertEquals("User Not Found.", userNotFoundException.getMessage());
		
		log.info("End");
	}

	@Test
	public void testNoArgConstructor() {
		log.info("Start");
		
		userNotFoundException = new UserNotFoundException();
		assertEquals(null, userNotFoundException.getMessage());
		
		log.info("End");
	}
}