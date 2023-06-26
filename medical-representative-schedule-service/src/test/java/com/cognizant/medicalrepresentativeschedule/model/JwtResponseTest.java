package com.cognizant.medicalrepresentativeschedule.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
public class JwtResponseTest {

	@Mock
	JwtResponse jwtResponse;

	@Before
	public void setUp() throws Exception {
		log.info("Start");

		jwtResponse = new JwtResponse("root", "root", true);

		log.info("End");
	}

	@Test
	public void testNoArgsConstructor() {
		log.info("Start");

		JwtResponse jwtResponse = new JwtResponse();
		assertEquals(null, jwtResponse.getId());
		assertEquals(null, jwtResponse.getName());
		assertEquals(false, jwtResponse.isValid());

		log.info("End");
	}
	
	@Test
	public void testAllArgsConstructor() {
		log.info("Start");

		jwtResponse = new JwtResponse("root", "root", true);
		assertEquals("root", jwtResponse.getId());
		assertEquals("root", jwtResponse.getName());
		assertEquals(true, jwtResponse.isValid());

		log.info("End");
	}

	@Test
	public void testGetters() throws Exception {
		log.info("Start");

		assertEquals("root", jwtResponse.getId());
		assertEquals("root", jwtResponse.getName());
		assertEquals(true, jwtResponse.isValid());

		log.info("End");
	}

	@Test
	public void testSetters() {
		log.info("Start");

		jwtResponse.setName("Rock");
		jwtResponse.setId("admin");
		jwtResponse.setValid(true);
		assertEquals("Rock", jwtResponse.getName());

		log.info("End");
	}

	@Test
	public void testEquals() {
		log.info("Start");

		boolean equals = jwtResponse.equals(jwtResponse);
		assertTrue(equals);

		log.info("End");
	}

	@Test
	public void testHashCode() {
		log.info("Start");

		int hashCode = jwtResponse.hashCode();
		assertEquals(hashCode, jwtResponse.hashCode());

		log.info("End");
	}

	@Test
	public void testToString() {
		log.info("Start");

		System.out.println(jwtResponse.toString());
		assertEquals("JwtResponse(id=" + jwtResponse.getId() + ", name=" + jwtResponse.getName() + ", valid="
				+ jwtResponse.isValid() + ")", jwtResponse.toString());

		log.info("End");
	}
}
