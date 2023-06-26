package com.cognizant.medicalrepresentativeschedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.model.JwtResponse;
import com.cognizant.medicalrepresentativeschedule.service.SessionValidationService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class provides implementation of SessionValidationService
 *
 */
@Slf4j
@Service
public class SessionValidationServiceImpl implements SessionValidationService {

	/**
	 * This interface uses Feign client to communicate with authorization service
	 */
	@Autowired
	private AuthenticationFeignClient authenticationFeignClient;
	
	/**
	 * This method validates the session by communicating with the authorization service
	 * using AuthenticationFeignClient
	 * 
	 * @param token
	 */
	@Override
	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		
		log.info("Start");
		
		JwtResponse response = authenticationFeignClient.verifyToken(token);
		if (!response.isValid()) {
			log.info("End");

			throw new TokenValidationFailedException("Invalid Token");
		}

		log.info("End");
		return true;
	}
	
}