package com.cognizant.medicinestock.exception;

import lombok.NoArgsConstructor;

/**
 * This is a user defined exception thrown when medicine name is not found in
 * the stock.
 */
@NoArgsConstructor
public class MedicineNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MedicineNotFoundException(String message) {
		super(message);
	}

}
