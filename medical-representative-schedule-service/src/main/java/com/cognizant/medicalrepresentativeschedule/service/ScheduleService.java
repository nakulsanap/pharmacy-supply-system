package com.cognizant.medicalrepresentativeschedule.service;

import java.time.LocalDate;
import java.util.List;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;

/**
 * This interface provides methods to Schedule meetings between doctors and Medical Representatives
 * 
 */
public interface ScheduleService {
	
	/**
	 * This method schedules meetings between doctors and medical representatives 
	 * from the start date provides as input
	 * and returns the list of schedules
	 * 
	 * @param token
	 * @param scheduleStartDate
	 * @return List<RepSchedule>
	 * @throws TokenValidationFailedException
	 */
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate) throws TokenValidationFailedException;

}
