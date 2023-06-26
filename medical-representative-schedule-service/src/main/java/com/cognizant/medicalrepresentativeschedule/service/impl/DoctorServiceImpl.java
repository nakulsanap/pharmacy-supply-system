package com.cognizant.medicalrepresentativeschedule.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.model.Doctor;
import com.cognizant.medicalrepresentativeschedule.service.DoctorService;
import com.cognizant.medicalrepresentativeschedule.util.CsvParseUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * This class provides the implementation for DoctorService interface. 
 *
 */
@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

	/**
	 *
	 * This method returns List of doctors from the Doctor.csv file
	 */
	@Override
	public List<Doctor> getDoctors() {
		
		log.info("Start");
		
		List<Doctor> doctors = CsvParseUtil.parseDoctors();
		
		log.info("End");
		
		return doctors;
	}
	
}
