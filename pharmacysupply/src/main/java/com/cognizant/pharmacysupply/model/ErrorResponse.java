package com.cognizant.pharmacysupply.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This is a model class used for custom error handling which have fields like
 * time stamp, status, reason and a message.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {



	private LocalDateTime timestamp;

	private HttpStatus status;

	private String reason;

	private String message;
	
	

}