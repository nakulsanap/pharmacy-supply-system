package com.cognizant.medicalrepresentativeschedule.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.medicalrepresentativeschedule.dao.MedicalRepresentativesRepository;
import com.cognizant.medicalrepresentativeschedule.feignclient.MedicineStockClient;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;

@SpringBootTest
class ScheduleServiceTest {
/*
	@Mock
	private MedicineStockClient medicineStockClient;

	@Mock
	private MedicalRepresentativesRepository medicalRepresentativeRepository;

	@Autowired
	private ScheduleService scheduleService;
	
	@Test
	void testGetRepSchedule() {

		List<RepSchedule> repSchedule = scheduleService.getRepSchedule(LocalDate.of(2021, 03, 22));
		
		assertFalse(repSchedule.isEmpty());
		
	}

	@Test
	void testGetCalendar() {
		fail("Not yet implemented");
	}
*/
}
