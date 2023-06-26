package com.cognizant.medicalrepresentativeschedule.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorResponse {
	private String message;
	private String reason;
	private HttpStatus status;
	private LocalDateTime timestamp;

	public String getMessage() {
		return message;
	}

	public String getReason() {
		return reason;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ErrorResponse [timestamp=" + timestamp + ", status=" + status + ", reason=" + reason + ", message="
				+ message + "]";
	}

}
