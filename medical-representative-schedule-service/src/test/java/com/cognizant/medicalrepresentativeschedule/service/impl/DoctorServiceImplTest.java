package com.cognizant.medicalrepresentativeschedule.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.medicalrepresentativeschedule.model.Doctor;
import com.cognizant.medicalrepresentativeschedule.service.DoctorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorServiceImplTest {
	
	@Autowired
	private DoctorService doctorService;

	@Test
	public void testGetDoctors() {
		List<Doctor> doctors = doctorService.getDoctors();

		Doctor doctor = doctors.get(0);
		assertEquals(1, doctor.getId());
		assertEquals("D1", doctor.getName());
		assertEquals("9449569825", doctor.getContactNumber());
		assertEquals("Orthopaedics", doctor.getTreatingAilment());
		
	}

}
