package com.cognizant.pharmacysupply.exception;

import lombok.NoArgsConstructor;

/**
 * This is a user defined exception which is thrown when the target ailment is
 * not found in the stock.
 */
@NoArgsConstructor
public class TokenValidationFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TokenValidationFailedException(String message) {
		super(message);
	}

}
