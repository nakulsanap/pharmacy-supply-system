package com.cognizant.medicinestock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicinestock.model.MedicineStock;
import com.cognizant.medicinestock.repository.MedicineStockRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * This class implements the MedicineStockService interface and provides
 * implementation for all the methods.
 *
 */
@Service
@Slf4j
public class MedicineStockServiceImpl implements MedicineStockService {

	@Autowired
	private MedicineStockRepository repository;

	/**
	 * This method uses the findAll method of the repository to get all the stock
	 * information and returns the list.
	 */
	@Override
	public List<MedicineStock> getMedicineStockInformation() {
		log.info("START");
		log.info("END");
		return repository.findAll();
	}

	/**
	 * This method uses the query defined in the Repository to get the medicine
	 * stock information based on target ailment and returns the same.
	 */
	@Override
	public List<MedicineStock> getMedicineByTargetAilment(String treatingAilment) {
		log.info("START");
		log.info("END");
		return repository.getMedicineByTargetAilment(treatingAilment);
	}
	
	/**
	 * This method uses the query defined in the Repository to get the number of tablets in the 
	 * stock for a specific medicine and returns the same.
	 */
	@Override
	public MedicineStock getNumberOfTabletsInStockByName(String medicine) {
		log.info("START");
		log.info("END");
		MedicineStock numberOfTabletsInStockByName = repository.getNumberOfTabletsInStockByName(medicine);
		log.debug("NUMBER OF TABLETS IN STOCK BY NAME {}:", numberOfTabletsInStockByName);
		return numberOfTabletsInStockByName;
	}
	
	/**
	 * This method uses the query defined in the Repository to update the number of tablets in the 
	 * stock for a specific medicine and returns either true or false.
	 */
	@Override
	public Boolean updateNumberOfTabletsInStockByName(String medicine, int count) {
		log.info("START");
		log.info(medicine + " ############# " + count);
		repository.updateNumberOfTabletsInStockByName(medicine, count);
		log.info("END");
		return true;
	}

}
