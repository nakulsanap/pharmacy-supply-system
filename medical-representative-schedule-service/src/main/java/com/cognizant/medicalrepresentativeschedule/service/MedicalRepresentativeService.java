package com.cognizant.medicalrepresentativeschedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.dao.MedicalRepresentativesRepository;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.model.JwtResponse;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;

@Service
public class MedicalRepresentativeService {

	@Autowired
	AuthenticationFeignClient authFeignClient;

	@Autowired
	private MedicalRepresentativesRepository medicalRepresentativesRepository;

	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException {
		
		if (!isValidSession(token)) {
			return null;
		}
		
		return medicalRepresentativesRepository.findAll();
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}
		
		return true;
	}

}
