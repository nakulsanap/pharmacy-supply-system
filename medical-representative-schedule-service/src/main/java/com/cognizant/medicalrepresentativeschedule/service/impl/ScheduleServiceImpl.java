package com.cognizant.medicalrepresentativeschedule.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.model.Doctor;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.service.DoctorService;
import com.cognizant.medicalrepresentativeschedule.service.MedicalRepresentativeService;
import com.cognizant.medicalrepresentativeschedule.service.MedicineStockService;
import com.cognizant.medicalrepresentativeschedule.service.ScheduleService;
import com.cognizant.medicalrepresentativeschedule.service.SessionValidationService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class provides implementation of ScheduleService
 *
 */
@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

	/**
	 * This interface has an implementation class SessionValidationServiceImpl that
	 * connects with authorization service to validate the request
	 */
	@Autowired
	private SessionValidationService sessionValidationService;
	
	/**
	 * This interface has an implementation class MedicineStockServiceImpl that
	 * connects with medicine-stock-service to retrieve medicines based on Treating Ailment
	 */
	@Autowired
	private MedicineStockService medicineStockService;
	
	/**
	 * This interface has an implementation class MedicalRepresentativeServiceImpl that
	 * retrieves MedicalRepresentatives from the database
	 */
	@Autowired
	private MedicalRepresentativeService medicalRepresentativeService;
	
	/**
	 * This interface has an implementation class DoctorServiceImpl that
	 * retrieves doctors from Doctor.csv file
	 */
	@Autowired
	private DoctorService doctorService;

	/**
	 * This method takes start date as input and schedules meeting 
	 * between doctors and Medical representatives at 1PM to 2PM for all days of week except Sunday
	 * If the current time is before 1 PM then the start date will be today else tomorrow
	 * 
	 * @param token
	 * @param scheduleStartDate
	 * @return repSchedules
	 */
	@Override
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate)
			throws TokenValidationFailedException {
		log.info("Start");

		if (!sessionValidationService.isValidSession(token)) {
			log.info("End");

			return null;
		}

		List<RepSchedule> repSchedules = new ArrayList<>();

		List<Doctor> doctors = doctorService.getDoctors();

		log.debug("docters : {}", doctors);

		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeService
				.getMedicalRepresentatives(token);

		log.debug("medicalRepresentatives : {}", medicalRepresentatives);

		LocalDate localDate = scheduleStartDate;

		LocalTime now = LocalTime.now();
		LocalTime one = LocalTime.of(13, 0);

		LocalDate today = LocalDate.now();
		if (scheduleStartDate.isBefore(today)) {

			log.info("End");
			return repSchedules;
		}

		if (scheduleStartDate.equals(today)) {

			if (now.isAfter(one)) {
				localDate = localDate.plusDays(1);
			}

		}

		repSchedules = getSchedules(token, localDate, doctors, medicalRepresentatives, repSchedules);

		log.info("End");

		return repSchedules;
	}

	
	/**
	 * 
	 * This method maps doctors with medical representatives for each day except Sunday
	 * 
	 * @param token
	 * @param localDate
	 * @param doctors
	 * @param medicalRepresentatives
	 * @param repSchedules
	 * @return repSchedules
	 */
	private List<RepSchedule> getSchedules(String token, LocalDate localDate, List<Doctor> doctors,
			List<MedicalRepresentative> medicalRepresentatives, List<RepSchedule> repSchedules) {
		log.info("Start");

		for (int i = 0; i < doctors.size(); i++) {

			if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				localDate = localDate.plusDays(1);
			}

			Doctor doctor = doctors.get(i);
			MedicalRepresentative medicalRepresentative = medicalRepresentatives.get(i % 3);
			RepSchedule repSchedule = buildRepSchedule(token, localDate, i + 1, doctor, medicalRepresentative);
			repSchedules.add(repSchedule);

			localDate = localDate.plusDays(1);
		}

		log.debug("repSchedules : {}", repSchedules);

		log.info("End");
		return repSchedules;
	}

	/**
	 * This method builds the RepSchedule Object 
	 * based on doctor and medical representative details
	 * 
	 * @param token
	 * @param localDate
	 * @param id
	 * @param doctor
	 * @param medicalRepresentative
	 * @return repSchedule
	 */
	private RepSchedule buildRepSchedule(String token, LocalDate localDate, int id, Doctor doctor,
			MedicalRepresentative medicalRepresentative) {
		log.info("Start");

		String[] medicinesByTreatingAilment = medicineStockService.getMedicinesByTreatingAilment(token,
				doctor.getTreatingAilment());

		RepSchedule repSchedule = new RepSchedule();

		repSchedule.setId(id);
		repSchedule.setRepName(medicalRepresentative.getName());
		repSchedule.setDoctorName(doctor.getName());
		repSchedule.setDoctorContactNumber(doctor.getContactNumber());
		repSchedule.setMeetingDate(localDate);
		repSchedule.setMeetingSlot("1 PM to 2 PM");
		repSchedule.setTreatingAilment(doctor.getTreatingAilment());
		repSchedule.setMedicines(medicinesByTreatingAilment);
		log.debug("repSchedule : {}", repSchedule);

		log.info("End");

		return repSchedule;
	}

}
