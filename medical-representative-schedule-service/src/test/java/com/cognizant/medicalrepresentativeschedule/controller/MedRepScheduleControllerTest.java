package com.cognizant.medicalrepresentativeschedule.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.medicalrepresentativeschedule.exception.InvalidDateException;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.service.ScheduleService;
import com.cognizant.medicalrepresentativeschedule.service.SessionValidationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedRepScheduleControllerTest {

	@Mock
	private AuthenticationFeignClient authenticationFeignClient;

	@Mock
	private SessionValidationService sessionValidationService;

	@Mock
	private ScheduleService scheduleService;

	@InjectMocks
	private MedRepScheduleController medRepScheduleController;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(medRepScheduleController).build();
	}

	@Test
	public void testGetRepSchedule_Forbidden() throws TokenValidationFailedException, Exception {
		when(sessionValidationService.isValidSession(Mockito.anyString())).thenReturn(false);

		mockMvc.perform(
				get("/RepSchedule/2022-02-03").header("Authorization", "token").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

	@Test(expected = Exception.class)
	public void testGetRepSchedule_InvalidDate() throws InvalidDateException, Exception {
		when(sessionValidationService.isValidSession(Mockito.anyString())).thenReturn(true);

		mockMvc.perform(
				get("/RepSchedule/dd-mm-yyyy").header("Authorization", "token").accept(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testGetRepSchedule_NotFound() throws TokenValidationFailedException, Exception {
		when(sessionValidationService.isValidSession(Mockito.anyString())).thenReturn(true);
		when(scheduleService.getRepSchedule(Mockito.anyString(), Mockito.any(LocalDate.class)))
				.thenReturn(new ArrayList<>());

		mockMvc.perform(
				get("/RepSchedule/2010-01-01").header("Authorization", "token").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

	@Test
	public void testGetRepSchedule() throws Exception {

		when(sessionValidationService.isValidSession(Mockito.anyString())).thenReturn(true);
//		when(authenticationFeignClient.verifyToken("token")).thenReturn(new JwtResponse("root", "root", true));

		when(scheduleService.getRepSchedule(Mockito.anyString(), Mockito.any(LocalDate.class)))
				.thenReturn(getMockRepSchedule());

		mockMvc.perform(
				get("/RepSchedule/2022-04-20").header("Authorization", "token").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(5))).andExpect(status().isOk());

	}

	private List<RepSchedule> getMockRepSchedule() {
		List<RepSchedule> repSchedules = new ArrayList<>();
		String[] medicines = { "Crocin", "Percocet" };

		repSchedules.add(new RepSchedule(1, "Medicine Representative 1", "D1", "1 PM to 2PM",
				LocalDate.of(2022, 04, 20), "9449569825", medicines, "General"));
		repSchedules.add(new RepSchedule(2, "Medicine Representative 2", "D2", "1 PM to 2PM",
				LocalDate.of(2022, 04, 21), "9312349825", medicines, "Cardiology"));
		repSchedules.add(new RepSchedule(3, "Medicine Representative 3", "D3", "1 PM to 2PM",
				LocalDate.of(2022, 04, 22), "9411133422", medicines, "General"));
		repSchedules.add(new RepSchedule(4, "Medicine Representative 4", "D4", "1 PM to 2PM",
				LocalDate.of(2022, 04, 23), "9412343422", medicines, "Orthopaedics"));
		repSchedules.add(new RepSchedule(5, "Medicine Representative 2", "D5", "1 PM to 2PM",
				LocalDate.of(2022, 04, 24), "9131413425", medicines, "Gynaecology"));

		return repSchedules;
	}

}
