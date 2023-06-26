package com.cognizant.medicalrepresentativeschedule.service;

import java.util.List;

import com.cognizant.medicalrepresentativeschedule.model.Doctor;



/**
 * This interface provides methods related to Doctor model
 * 
 */
public interface DoctorService {

	/**
	 * This method retrieves doctors from the Doctor.csv file.
	 * 
	 * @return List<Doctor>
	 */
	List<Doctor> getDoctors();

}
