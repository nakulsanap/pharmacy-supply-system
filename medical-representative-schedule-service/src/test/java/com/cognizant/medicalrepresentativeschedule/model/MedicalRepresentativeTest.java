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
public class MedicalRepresentativeTest {

	@Mock
	private MedicalRepresentative medicalRepresentative;

	@Before
	public void setup() {
		log.info("Start");

		medicalRepresentative = new MedicalRepresentative();
		medicalRepresentative.setId(1);
		medicalRepresentative.setName("R1");
		medicalRepresentative.setPhoneNumber("8877779292");

		log.info("End");
	}

	@Test
	public void testNoArgsConstructor() {
		log.info("Start");

		medicalRepresentative = new MedicalRepresentative();
		assertEquals(0, medicalRepresentative.getId());
		assertEquals(null, medicalRepresentative.getName());
		assertEquals(null, medicalRepresentative.getPhoneNumber());

		log.info("End");
	}

	@Test
	public void testAllArgsConstructor() {
		log.info("Start");

		MedicalRepresentative medicalRepresentatives = new MedicalRepresentative(1, "R1", "8877779292");
		assertEquals("R1", medicalRepresentatives.getName());

		log.info("End");
	}

	@Test
	public void testGetters() throws Exception {
		log.info("Start");

		assertEquals(1, medicalRepresentative.getId());
		assertEquals("R1", medicalRepresentative.getName());
		assertEquals("8877779292", medicalRepresentative.getPhoneNumber());

		log.info("End");

	}

	@Test
	public void testSetters() {
		log.info("Start");

		medicalRepresentative.setId(1);
		assertEquals(1, medicalRepresentative.getId());

		log.info("End");
	}

	@Test
	public void testEquals() {
		log.info("Start");

		boolean equals = medicalRepresentative.equals(medicalRepresentative);
		assertTrue(equals);

		log.info("End");
	}

	@Test
	public void testHashCode() {
		log.info("Start");

		int hashCode = medicalRepresentative.hashCode();
		assertEquals(hashCode, medicalRepresentative.hashCode());

		log.info("End");
	}

	@Test
	public void testToString() {
		log.info("Start");

		assertEquals("MedicalRepresentative(id=" + medicalRepresentative.getId() + ", name="
				+ medicalRepresentative.getName() + ", phoneNumber=" + medicalRepresentative.getPhoneNumber() + ")",
				medicalRepresentative.toString());

		log.info("End");
	}
}