package com.cognizant.medicalrepresentativeschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible for creating all objects, dependency injection and
 * managing the life cycle of all beans
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class MedRepScheduleServiceApplication {

	/**
	 * This method starts the Spring boot application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Start");

		SpringApplication.run(MedRepScheduleServiceApplication.class, args);
		log.info("End");
	}

}
