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
import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.feignclient.MedicineStockClient;
import com.cognizant.medicalrepresentativeschedule.model.Doctor;
import com.cognizant.medicalrepresentativeschedule.model.JwtResponse;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.service.MedicalRepresentativeService;
import com.cognizant.medicalrepresentativeschedule.service.ScheduleService;
import com.cognizant.medicalrepresentativeschedule.util.CsvParseUtil;
import com.cognizant.medicalrepresentativeschedule.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private MedicalRepresentativeService medicalRepresentativeService;

	@Autowired
	AuthenticationFeignClient authFeignClient;

	@Autowired
	private MedicineStockClient medicineStockClient;

	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public ResponseEntity<List<RepSchedule>> getRepSchedule(@RequestHeader(name = "Authorization") String token,
			@PathVariable("scheduleStartDate") String scheduleStartDate)
			throws InvalidDateException, TokenValidationFailedException {

		log.debug("scheduleStartDate : {}", scheduleStartDate);

		List<RepSchedule> repSchedule = null;

		LocalDate localDate = DateUtil.getDate(token, scheduleStartDate);
		log.debug("date : {}", localDate);

		if (!scheduleService.isValidSession(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		if (localDate == null) {
			throw new InvalidDateException("Invalid date");
		}

		repSchedule = scheduleService.getRepSchedule(token, localDate);

		if (repSchedule.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.of(Optional.of(repSchedule));

	}

	@GetMapping
	public ResponseEntity<String[]> getMedicinesByTreatingAilment(@RequestHeader(name = "Authorization") String token) {
		return ResponseEntity.of(Optional.of(medicineStockClient.getMedicinesByTreatingAilment(token,"medicine")));
	}

	@GetMapping("/medicalRepresentatives")
	public List<MedicalRepresentative> getMedicalRepresentatives(@RequestHeader(name = "Authorization") String token)
			throws TokenValidationFailedException {
		if (medicalRepresentativeService.isValidSession(token))
			return medicalRepresentativeService.getMedicalRepresentatives(token);
		throw new TokenValidationFailedException("Invalid Token");

	}

	@GetMapping("/doctors")
	public List<Doctor> getDoctors(@RequestHeader(name = "Authorization") String token)
			throws TokenValidationFailedException {
		if (scheduleService.isValidSession(token))
			return CsvParseUtil.parseDoctors(token);

		throw new TokenValidationFailedException("Token Invalid");
	}
}
