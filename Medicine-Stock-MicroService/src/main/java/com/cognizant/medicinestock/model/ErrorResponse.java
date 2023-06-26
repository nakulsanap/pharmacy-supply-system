package com.cognizant.medicinestock.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This is a model class used for custom error handling which have fields like
 * time stamp, status, reason and a message.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {

	private LocalDateTime localDateTime;
	private HttpStatus status;
	private String reason;
	private String message;
	
}
