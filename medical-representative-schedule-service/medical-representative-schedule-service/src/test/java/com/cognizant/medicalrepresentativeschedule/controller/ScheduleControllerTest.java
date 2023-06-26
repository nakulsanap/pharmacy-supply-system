package com.cognizant.medicalrepresentativeschedule.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.service.ScheduleService;

@AutoConfigureMockMvc
@WebMvcTest(value = ScheduleController.class)
@SpringBootTest
class ScheduleControllerTest {
/*
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@MockBean
	private ScheduleService scheduleService;

	public List<RepSchedule> getMockRepSchedule() {
		List<RepSchedule> repSchedules = new ArrayList<>();
		
		RepSchedule mockRepSchedule = new RepSchedule();
		mockRepSchedule.setDoctorName("D1");
		mockRepSchedule.setMeetingSlot("1 PM to 2 PM");
		mockRepSchedule.setMeetingDate(LocalDate.of(2022, 04, 20));
		mockRepSchedule.setDoctorContactNumber("9449569825");
		mockRepSchedule.setMedicines("Medicine One, Medicine Two, Medicine Three");
		repSchedules.add(mockRepSchedule);
		
		return repSchedules;
	}

	@Test
	public void retrieveDetailsForCourse() throws Exception {
		List<RepSchedule> repSchedules = getMockRepSchedule();

		Mockito.when(scheduleService.getRepSchedule(Mockito.any(LocalDate.class))).thenReturn(repSchedules);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/RepSchedule/20-04-2022").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:Course1,name:Spring,description:10Steps}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K
		// Students","steps":["Learn Maven","Import Project","First Example","Second
		// Example"]}

//		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

//	@Test
//	void testGetRepSchedule() throws Exception {
//		ResultActions resultActions = mockMvc.perform(get("/RepSchedule/20-04-2022"));
//		resultActions.andExpect(status().isOk());
//		String content = mockMvc.getResponse().getContentAsString();
//		mockMvc.mapFromJson(content, RepSchedule[].class);
//		assertTrue(productlist.length > 0);
//
//		resultActions.andExpect(jsonPath("doctorName").exists());
//		resultActions.andExpect(jsonPath("doctorName").value("D1"));
////		resultActions.andExpect(jsonPath("meetingDate").doesNotExist());
//
//	}

	@Test
	void testGetMedicinesByTreatingAilment() {
		
	}

	@Test
	void testGetMedicalRepresentatives() {
	}

	@Test
	void testGetDoctors() {
	}

*/
}
