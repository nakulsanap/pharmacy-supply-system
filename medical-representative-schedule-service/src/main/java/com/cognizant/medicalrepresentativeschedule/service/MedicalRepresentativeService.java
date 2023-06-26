package com.cognizant.medicalrepresentativeschedule.service;

import java.util.List;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;

/**
 * This interface provides methods related to MedicalRepresentative model
 * 
 */
public interface MedicalRepresentativeService {
	
	/**
	 * 
	 * This method gets MedicalRepresentatives from the database
	 * 
	 * @param token
	 * @return List<MedicalRepresentative>
	 * @throws TokenValidationFailedException
	 */
	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException;

}
