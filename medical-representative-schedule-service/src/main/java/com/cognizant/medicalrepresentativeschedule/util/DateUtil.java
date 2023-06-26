package com.cognizant.medicalrepresentativeschedule.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.cognizant.medicalrepresentativeschedule.exception.InvalidDateException;

import lombok.extern.slf4j.Slf4j;

/**
 * This class contain methods related to Date
 *
 */
@Slf4j
public class DateUtil {

	/**
	 * This method accepts a string as input 
	 * parses the string to get a LocalDate
	 * throws InvalidDateException for invalid date format in the input string
	 * 
	 * @param scheduleStartDate
	 * @return localDate
	 * @throws InvalidDateException
	 */
	public static LocalDate getDate(String scheduleStartDate) throws InvalidDateException {

		LocalDate localDate = null;
		try {

			log.info("Start");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
			localDate = LocalDate.parse(scheduleStartDate, formatter);

			log.debug("localDate : {}", localDate);

		} catch (Exception e) {
			log.error("Date Format Exception");
			throw new InvalidDateException("Invalid Date");
		}

		log.info("End");

		return localDate;
	}

}
