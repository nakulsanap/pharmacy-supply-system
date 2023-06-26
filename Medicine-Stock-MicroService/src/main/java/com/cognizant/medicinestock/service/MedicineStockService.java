package com.cognizant.medicinestock.service;

import java.util.List;

import com.cognizant.medicinestock.model.MedicineStock;

/**
 * This is an interface containing all the methods used for fetching details
 * from the repository.
 */
public interface MedicineStockService {

	public List<MedicineStock> getMedicineStockInformation();

	public List<MedicineStock> getMedicineByTargetAilment(String treatingAilment);

	public MedicineStock getNumberOfTabletsInStockByName(String medicine);

	public Boolean updateNumberOfTabletsInStockByName(String medicine, int count);
}
