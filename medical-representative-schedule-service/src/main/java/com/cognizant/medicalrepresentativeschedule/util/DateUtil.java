package com.cognizant.medicalrepresentativeschedule.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {

	
	public static LocalDate getDate(String token, String scheduleStartDate) throws TokenValidationFailedException {
		LocalDate localDate = null;
		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
			localDate = LocalDate.parse(scheduleStartDate, formatter);

			log.debug("date : {}", localDate);

		} catch (Exception e) {
			log.debug("Date Format Exception");
		}

		return localDate;
	}

}
