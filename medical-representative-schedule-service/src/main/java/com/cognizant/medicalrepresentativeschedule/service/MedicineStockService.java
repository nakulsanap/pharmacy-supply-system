package com.cognizant.medicalrepresentativeschedule.service;

/**
 * This interface provides methods related to MedicineStock model
 * 
 */
public interface MedicineStockService {

	/**
	 * This method communicates with the medicine-stock-service and gets medicines based on Treating Ailment
	 * 
	 * @param token
	 * @param treatingAilment
	 * @return
	 */
	public String[] getMedicinesByTreatingAilment(String token, String treatingAilment);
	
}
