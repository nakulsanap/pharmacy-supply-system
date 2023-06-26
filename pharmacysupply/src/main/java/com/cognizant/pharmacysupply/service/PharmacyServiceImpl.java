package com.cognizant.pharmacysupply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.feignclient.AuthenticationFeignClient;
import com.cognizant.pharmacysupply.feignclient.MedicineStockFeignClient;
import com.cognizant.pharmacysupply.model.JwtResponse;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineStock;
import com.cognizant.pharmacysupply.model.MedicineSupply;
import com.cognizant.pharmacysupply.repository.MedicineDemandRepository;
import com.cognizant.pharmacysupply.repository.PharmacyMedicineSupplyRepository;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

/**
 * This class implements the PharmacyService interface and provide
 * implementation for all the methods
 *
 */
@Slf4j
@Service
public class PharmacyServiceImpl implements PharmacyService {
	@Autowired
	private MedicineDemandRepository medicineDemandRepo;

	@Autowired
	private MedicineStockFeignClient stockFeignClient;

	@Autowired
	private PharmacyMedicineSupplyRepository pharmacyMedicineSupplyRepository;

	@Autowired
	private AuthenticationFeignClient authFeign;

	@Autowired
	private MedicineDemandRepository medicineDemandRepository;

	/**
	 * Get the count of supply from stock on giving the input as demand count. We
	 * are retrieving the supply count from the medicine stock service. And if the
	 * stock contains enough medicine to supply then the stock gets updated else
	 * Medicine out of stock will be displayed.
	 * 
	 * @param --> token
	 * @param --> medicineDemandList
	 * @return --> This method returns the List of Medicine Supply
	 */
	@Override
	public List<MedicineSupply> getPharmacySupplyCount(String token, List<MedicineDemand> medicineDemandList)
			throws MedicineNotFoundException {
		log.info("Start");
		log.info("Medicine Demand List {} ", medicineDemandList);
		List<MedicineSupply> list = new ArrayList<>();

		for (MedicineDemand medicineDemand : medicineDemandList) {
			MedicineSupply pharmacyMedicineSupply = new MedicineSupply();
			MedicineStock medicineStock = getNumberOfTablets(token, medicineDemand);
			log.info("{}", medicineStock);
			log.info("Medicine {} , Tablets {}", medicineDemand.getMedicineName(),
					medicineStock.getNumberOfTabletsInStock());

			if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
				return null;
			}

			setSupply(token, pharmacyMedicineSupply, medicineDemand, medicineStock);
			list.add(pharmacyMedicineSupply);
		}
		pharmacyMedicineSupplyRepository.saveAll(list);
		medicineDemandRepository.saveAll(medicineDemandList);
		log.info("End");
		return list;
	}

	/**
	 * This method is used to supply the demanded number of medicine.
	 * 
	 * @param token
	 * @param medicineSupply
	 * @param medicineDemand
	 * @param medicineStock
	 * @throws MedicineNotFoundException if medicine is not found in the stock.
	 */
	public void setSupply(String token, MedicineSupply medicineSupply, MedicineDemand medicineDemand,
			MedicineStock medicineStock) throws MedicineNotFoundException {
		log.info("Start");
		int val = 0;
		log.info("number of tablets {}", medicineStock.getNumberOfTabletsInStock());
		if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
			medicineSupply.setSupplyCount(medicineStock.getNumberOfTabletsInStock());

		} else {
			medicineSupply.setSupplyCount(medicineDemand.getDemandCount());
			val = medicineStock.getNumberOfTabletsInStock() - medicineDemand.getDemandCount();

		}
		log.info("val = {}", val);
		try {
			stockFeignClient.updateNumberOfTabletsInStockByName(token, medicineDemand.getMedicineName(), val);
		} catch (FeignException ex) {
			throw new MedicineNotFoundException("Medicine not found");
		}
		medicineSupply.setMedicineName(medicineDemand.getMedicineName());
		log.info("medicineDemand {} medicineSupply {}", medicineDemand, medicineSupply);
		medicineSupply.setPharmacyName(medicineStock.getPharmacyName());
		log.info("medicineSupply{}:", medicineSupply);
		log.info("End");
	}

	/**
	 *
	 * @param token
	 * @param medicineDemand
	 * @return MedicineStock information for the particular medicine.
	 * @throws MedicineNotFoundException if medicine is not found in the stock.
	 */
	public MedicineStock getNumberOfTablets(String token, MedicineDemand medicineDemand)
			throws MedicineNotFoundException {
		log.info("Start");
		MedicineStock medicineStock = null;
		log.info("Medicine : {}", medicineDemand);
		try {
			medicineStock = stockFeignClient.getNumberOfTabletsInStockByName(token, medicineDemand.getMedicineName());
		} catch (FeignException ex) {
			throw new MedicineNotFoundException("Medicine not found");
		}

		if (medicineStock == null) {
			throw new MedicineNotFoundException("Medicine not found");
		}
		log.info("End");
		return medicineStock;
	}

	/**
	 * From the database we are fetching all the MedicineDemand. We are invoking
	 * method findAll() which is present in the Medicine Demand Repository
	 * interface.
	 * 
	 * @return List of medicine demand
	 */

	@Override
	public List<MedicineDemand> getMedicineDemand() {
		log.info("Start");
		return medicineDemandRepo.findAll();
	}

	/**
	 * From the database we are fetching all the MedicineSupply. We are invoking
	 * method findAll() which is present in the Medicine Supply Repository
	 * interface.
	 * 
	 * @return List of medicine supply
	 */

	@Override
	public List<MedicineSupply> getMedicineSupply() {
		log.info("Start");
		return pharmacyMedicineSupplyRepository.findAll();
	}

	/**
	 * Validates if the provided token is valid or not. It is checked before every
	 * request.
	 * 
	 * @return true if valid token else returns false.
	 */
	@Override
	public Boolean validateToken(String token) {
		log.info("Start");

		JwtResponse jwtResponse = authFeign.verifyToken(token);
		log.info("End");

		if (jwtResponse.isValid())
			return true;
		throw new TokenValidationFailedException("Token is no longer valid");

	}
}
