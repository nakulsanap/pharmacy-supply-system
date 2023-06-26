package com.cognizant.medicalrepresentativeschedule.service.impl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedule.service.MedicalRepresentativeService;
import com.cognizant.medicalrepresentativeschedule.service.SessionValidationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalRepresentativeServiceImplTest {

	@Autowired
	MedicalRepresentativeService medicalRepresentativeService;
	
	@MockBean
	SessionValidationService sessionValidationService;
	
	@Test
	public void testGetMedicalRepresentatives() throws TokenValidationFailedException {

		when(sessionValidationService.isValidSession("token")).thenReturn(true);
		
		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeService.getMedicalRepresentatives("token");
		
		assertEquals(3, medicalRepresentatives.size());
		
	}
	@Test
	public void testgetNullForMedicalRepresentativesList() throws TokenValidationFailedException {
		
		when(sessionValidationService.isValidSession("token")).thenReturn(false);
		
		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeService.getMedicalRepresentatives("token");
		
		assertNull(medicalRepresentatives);
	}
	
}
