package com.cognizant.medicinestock.exception;

import lombok.NoArgsConstructor;

/**
 * This is a user defined exception which is thrown when the target ailment is
 * not found in the stock.
 */
@NoArgsConstructor
public class TreatingAilmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TreatingAilmentNotFoundException(String message) {
		super(message);
	}

}
