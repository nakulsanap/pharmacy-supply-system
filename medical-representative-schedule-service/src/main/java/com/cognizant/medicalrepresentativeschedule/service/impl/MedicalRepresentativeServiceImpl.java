package com.cognizant.medicalrepresentativeschedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.dao.MedicalRepresentativeRepository;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedule.service.MedicalRepresentativeService;
import com.cognizant.medicalrepresentativeschedule.service.SessionValidationService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class provides implementation of MedicalRepresentativeService
 *
 */
@Slf4j
@Service
public class MedicalRepresentativeServiceImpl implements MedicalRepresentativeService {

	@Autowired
	private MedicalRepresentativeRepository medicalRepresentativesRepository;
	
	@Autowired
	private SessionValidationService sessionValidationService;

	/**
	 * This method checks if the session is valid by using SessionValidationService
	 * and then retrieves MedicalRepresentatives from the database
	 *
	 */
	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException {

		log.info("Start");

		log.debug("token : {}", token);

		if (!sessionValidationService.isValidSession(token)) {
			log.info("End");

			return null;
		}

		log.info("End");
		return medicalRepresentativesRepository.findAll();
	}
	
}
