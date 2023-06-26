package com.cognizant.pharmacysupply.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineSupply;
import com.cognizant.pharmacysupply.service.PharmacyServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PharmacyController {

	@Autowired
	private PharmacyServiceImpl pharmacyService;

	/**
	 * This method takes medicine demand as input checks if there is enough stock to
	 * fulfill the demand, if sufficient stock is available then it supplies the
	 * medicine else it will not supply
	 * 
	 * @param token
	 * @param medicineDemandList
	 * @return the Pharmacy supply and status as OK.
	 * @throws MedicineNotFoundException
	 */
	@GetMapping("/ping")
	public String ping() {
		return "pharmacy supply service is up";
	}
	@PostMapping("/pharmacy-supply")
	public ResponseEntity<?> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@Valid @RequestBody List<MedicineDemand> medicineDemandList) throws MedicineNotFoundException {
		log.info("Start");

		log.debug("medicineDemandList {}:", medicineDemandList);
		List<MedicineSupply> pharmacySupply = null;
		if (pharmacyService.validateToken(token)) {
			pharmacySupply = pharmacyService.getPharmacySupplyCount(token, medicineDemandList);

			if (pharmacySupply == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			log.info("End");
			return new ResponseEntity<>(pharmacySupply, HttpStatus.OK);
		}
		log.info("End");
		throw new TokenValidationFailedException("Token is no longer valid");
	}

	/**
	 * @param token
	 * @return This method returns the medicine supplied till date if the token is
	 *         valid else throws TokenValidationFailedException.
	 */
	@GetMapping("/getMedicineSupply")
	public ResponseEntity<?> getMedicineSupply(@RequestHeader("Authorization") String token) {
		List<MedicineSupply> medicineSupply = null;
		if (pharmacyService.validateToken(token)) {
			medicineSupply = pharmacyService.getMedicineSupply();
			return new ResponseEntity<>(medicineSupply, HttpStatus.OK);
		}
		throw new TokenValidationFailedException("Token is no longer valid");
	}


	/**
	 * @param token
	 * @return This method returns the medicine demanded till this time if the token
	 *         is valid else throws exception.
	 */
	@GetMapping("/getMedicineDemand")
	public ResponseEntity<?> getMedicineDemand(@RequestHeader(name = "Authorization") String token) {
		List<MedicineDemand> medicineDemand = null;
		if (pharmacyService.validateToken(token)) {
			medicineDemand = pharmacyService.getMedicineDemand();
			return new ResponseEntity<>(medicineDemand, HttpStatus.OK);
		}
		throw new TokenValidationFailedException("Token is no longer valid");
	}

}
