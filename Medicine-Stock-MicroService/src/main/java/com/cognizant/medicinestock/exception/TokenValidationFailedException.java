package com.cognizant.medicinestock.exception;

import lombok.NoArgsConstructor;

/**
 * This is a User defined exception which is thrown when the token is not valid.
 *
 */
@NoArgsConstructor
public class TokenValidationFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenValidationFailedException(String message) {
		super(message);
	}

}
