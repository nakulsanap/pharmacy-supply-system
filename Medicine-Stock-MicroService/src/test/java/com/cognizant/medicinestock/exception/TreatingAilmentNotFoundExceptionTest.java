package com.cognizant.medicinestock.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class contains test cases for the TokenValidationFailedException class which
 * are written using junit and mockito
 * 
 * @author Pod3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TreatingAilmentNotFoundExceptionTest {

	@Mock
	private TreatingAilmentNotFoundException treatingAilmentNotFound;

	@Test
	public void testOneArgConstructor() {
		TreatingAilmentNotFoundException treatingAilmentNotFound = new TreatingAilmentNotFoundException(
				"Ailment Not Found");
		assertEquals("Ailment Not Found", treatingAilmentNotFound.getMessage());
	}
	
	@Test
	public void testNoArgsConstructor() {
		TreatingAilmentNotFoundException exception = new TreatingAilmentNotFoundException();
		assertEquals(null, exception.getMessage());
	}

}
