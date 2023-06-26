package com.cognizant.medicalrepresentativeschedule.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.medicalrepresentativeschedule.exception.InvalidDateException;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.service.ScheduleService;
import com.cognizant.medicalrepresentativeschedule.service.SessionValidationService;
import com.cognizant.medicalrepresentativeschedule.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is handling all the end points for RepScheduleService. This
 * controller has mappings which will be used to display schedule based on date
 * provided.
 */
@Slf4j
@RestController
public class MedRepScheduleController {

	/**
	 * This interface has an implementation class SessionValidationServiceImpl that
	 * connects with authorization service to validate the request. 
	 */
	@Autowired
	private SessionValidationService sessionValidationService;
	
	/**
	 * This interface has an implementation class ScheduleServiceImpl that
	 * contains the business logic for generating the schedule
	 */
	@Autowired
	private ScheduleService scheduleService;
	
	/**
	 * This method will check if the token is valid by using SessionValidationService. If invalid then it will throw TokenValidationFailedException
	 * Then it will check if the input date is valid. If invalid then it will throw InvalidDateException
	 * and then return the schedule between doctors and medical representatives of Pharmacy company. 
	 * 
	 * @param token
	 * @param scheduleStartDate
	 * @return ResponseEntity<List<RepSchedule>>
	 * @throws InvalidDateException
	 * @throws TokenValidationFailedException
	 */

	@GetMapping("/ping")
	public String ping() {
		return "medical-representative-schedule service is up";
	}
	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public ResponseEntity<List<RepSchedule>> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("scheduleStartDate") final String scheduleStartDate)
			throws InvalidDateException, TokenValidationFailedException {
		log.info("Start");
		
		log.debug("scheduleStartDate : {}", scheduleStartDate);

		List<RepSchedule> repSchedule = null;

		LocalDate localDate = DateUtil.getDate(scheduleStartDate);
		log.debug("localDate : {}", localDate);

		log.debug("sessionValidationService : {}", sessionValidationService);

		if (!sessionValidationService.isValidSession(token)) {
			log.info("End");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		repSchedule = scheduleService.getRepSchedule(token, localDate);

		log.debug("repSchedule : {}", repSchedule);

		if (repSchedule.isEmpty()) {
			log.info("End");

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		log.info("End");

		return ResponseEntity.of(Optional.of(repSchedule));

	}

}
