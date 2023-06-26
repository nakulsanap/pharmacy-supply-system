package com.cognizant.medicalrepresentativeschedule.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.model.Doctor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvParseUtil {

	
	public static List<Doctor> parseDoctors(String token) throws TokenValidationFailedException {

		final List<Doctor> doctors = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:Doctor.csv")))) {

			String line = null;

			while ((line = br.readLine()) != null) {
				String[] entry = line.split(",");

				Doctor doctor = new Doctor(Integer.parseInt(entry[0]), entry[1], entry[2], entry[3]);

				doctors.add(doctor);
			}

		} catch (IOException e) {
			log.error("File not found");
		}

		log.info("End");

		return doctors;
	}
}
