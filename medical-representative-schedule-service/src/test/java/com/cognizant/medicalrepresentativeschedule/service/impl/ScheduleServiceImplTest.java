package com.cognizant.medicalrepresentativeschedule.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.model.Doctor;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.service.MedicineStockService;
import com.cognizant.medicalrepresentativeschedule.service.ScheduleService;
import com.cognizant.medicalrepresentativeschedule.service.SessionValidationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceImplTest {
	
	@MockBean
	private SessionValidationService sessionValidationService;
	
	@MockBean
	private MedicineStockService medicineStockService;

	@Autowired
	private ScheduleService scheduleService;

	List<Date> scheduleDates;

	List<Doctor> doctors;
	List<MedicalRepresentative> medicalRepresentatives;

	@Before
	public void setup() {
		
		String[] medicines = { "Crocin", "Percocet" };
		RepSchedule repSchedule = new RepSchedule();
		repSchedule.setId(1);
		repSchedule.setRepName("R1");
		repSchedule.setDoctorName("D1");
		repSchedule.setTreatingAilment("Orthopaedics");
		repSchedule.setMedicines(medicines);
		repSchedule.setMeetingSlot("1 AM to 2 PM");
		repSchedule.setMeetingDate(LocalDate.of(2020, 02, 03));
		repSchedule.setDoctorContactNumber("784521487");
		
		doctors = new ArrayList<Doctor>();
		doctors.add(new Doctor(1, "Doc 1", "9412312312", "General"));
		doctors.add(new Doctor(2, "Doc 2", "9823413213", "Cardiology"));
		doctors.add(new Doctor(3, "Doc 3", "9712312312", "General"));
		doctors.add(new Doctor(4, "Doc 4", "9812312312", "Orthopaedics"));
		doctors.add(new Doctor(5, "Doc 5", "9812312321", "Gynaecology"));
		
		medicalRepresentatives = new ArrayList<MedicalRepresentative>();
		medicalRepresentatives.add(new MedicalRepresentative(1, "Med Rep 1", "92312312312"));
		medicalRepresentatives.add(new MedicalRepresentative(2, "Med Rep 2", "93423423423"));
		medicalRepresentatives.add(new MedicalRepresentative(3, "Med Rep 3", "91231233214"));
	}

	@Test
	public void testGetRepScheduleInvalidToken() throws TokenValidationFailedException {
		log.info("Start");
		
		when(sessionValidationService.isValidSession(Mockito.anyString())).thenReturn(false);
		
		List<RepSchedule> repSchedules = scheduleService.getRepSchedule("token", LocalDate.of(2022, 2, 2));

		assertNull(repSchedules);
		
		log.info("End");
	}

	@Test
	public void testGetRepScheduleStartDateBeforeToday() throws TokenValidationFailedException {
		log.info("Start");

		when(sessionValidationService.isValidSession(Mockito.anyString())).thenReturn(false);

		List<RepSchedule> repSchedules = scheduleService.getRepSchedule("token", LocalDate.of(2020, 2, 2));

		assertNull(repSchedules);

		log.info("End");
	}

	@Test
	public void testGetRepScheduleStartDateToday() throws TokenValidationFailedException {
		log.info("Start");
		
		when(sessionValidationService.isValidSession(Mockito.anyString())).thenReturn(true);
		when(medicineStockService.getMedicinesByTreatingAilment(Mockito.anyString(), Mockito.anyString())).thenReturn(new String[] {});

		List<RepSchedule> repSchedules = scheduleService.getRepSchedule("token", LocalDate.now());

		assertEquals(5, repSchedules.size());
		
		if (LocalTime.now().isBefore(LocalTime.of(13, 0))) {
			assertEquals(LocalDate.now().getDayOfMonth(), repSchedules.get(0).getMeetingDate().getDayOfMonth());
		}
		else {
			assertEquals(LocalDate.now().plusDays(1).getDayOfMonth(), repSchedules.get(0).getMeetingDate().getDayOfMonth());
		}
		
		log.info("End");
	}

}
