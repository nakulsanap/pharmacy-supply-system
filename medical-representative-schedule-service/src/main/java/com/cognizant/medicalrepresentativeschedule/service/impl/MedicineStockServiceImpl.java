package com.cognizant.medicalrepresentativeschedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.feignclient.MedicineStockFeignClient;
import com.cognizant.medicalrepresentativeschedule.service.MedicineStockService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class provides implementation of MedicineStockService
 *
 */
@Slf4j
@Service
public class MedicineStockServiceImpl implements MedicineStockService {

	@Autowired
	private MedicineStockFeignClient medicineStockFeignClient;
	
	/**
	 * This method connects with the medicine-stock-service 
	 * and retrieves medicines based on treating ailment
	 *
	 */
	@Override
	public String[] getMedicinesByTreatingAilment(String token, String treatingAilment) {
		log.info("Start");

		String[] medicines = medicineStockFeignClient.getMedicinesByTreatingAilment(token, treatingAilment);
		
		log.info("End");
		return medicines;
	}
	
}
