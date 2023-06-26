package com.cognizant.medicalrepresentativeschedule.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DoctorTest {

	@Mock
	public Doctor doctor;

	@Before
	public void setup() {
		log.info("Start");

		doctor = new Doctor();
		doctor.setId(1);
		doctor.setName("D1");
		doctor.setContactNumber("8877779292");
		doctor.setTreatingAilment("General");

		log.info("End");
	}

	@Test
	public void testNoArgsConstructor() {
		log.info("Start");

		doctor = new Doctor();
		assertEquals(0, doctor.getId());
		assertEquals(null, doctor.getName());
		assertEquals(null, doctor.getContactNumber());
		assertEquals(null, doctor.getTreatingAilment());

		log.info("End");
	}

	@Test
	public void testAllArgsConstructor() {
		log.info("Start");

		Doctor doctor = new Doctor(1, "D1", "8877779292", "General");
		assertEquals("D1", doctor.getName());

		log.info("End");
	}

	@Test
	public void testGetters() throws Exception {
		log.info("Start");

		assertEquals(1, doctor.getId());
		assertEquals("D1", doctor.getName());
		assertEquals("8877779292", doctor.getContactNumber());
		assertEquals("General", doctor.getTreatingAilment());

		log.info("End");
	}

	@Test
	public void testSetters() {
		log.info("Start");

		doctor.setName("D2");
		assertEquals("D2", doctor.getName());

		log.info("End");
	}

	@Test
	public void testHashCode() {
		log.info("Start");

		int hashCode = doctor.hashCode();
		assertEquals(hashCode, doctor.hashCode());

		log.info("End");
	}

	@Test
	public void testEquals() {
		log.info("Start");

		boolean equals = doctor.equals(doctor);
		assertTrue(equals);

		log.info("End");
	}

	@Test
	public void testToString() {
		log.info("Start");

		assertEquals(
				"Doctor(id=" + doctor.getId() + ", name=" + doctor.getName() + ", contactNumber="
						+ doctor.getContactNumber() + ", treatingAilment=" + doctor.getTreatingAilment() + ")",
				doctor.toString());

		log.info("End");
	}

}