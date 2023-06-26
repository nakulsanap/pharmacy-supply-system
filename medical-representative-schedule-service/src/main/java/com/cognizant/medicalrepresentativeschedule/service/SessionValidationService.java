package com.cognizant.medicalrepresentativeschedule.service;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;

/**
 * This interface provides methods related to SessionValidations
 *
 */
public interface SessionValidationService {
	
	/**
	 * 
	 * This method checks if the session is valid by taking token as input
	 * 
	 * @param token
	 * @return Boolean
	 * @throws TokenValidationFailedException
	 */
	Boolean isValidSession(String token) throws TokenValidationFailedException;
}
