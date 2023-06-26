package com.cognizant.medicalrepresentativeschedule.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentativeschedule.dao.MedicalRepresentativesRepository;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.feignclient.MedicineStockClient;
import com.cognizant.medicalrepresentativeschedule.model.Doctor;
import com.cognizant.medicalrepresentativeschedule.model.JwtResponse;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;
import com.cognizant.medicalrepresentativeschedule.util.CsvParseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduleService {

	@Autowired
	private MedicineStockClient medicineStockClient;

	@Autowired
	private MedicalRepresentativesRepository medicalRepresentativeRepository;

	@Autowired
	AuthenticationFeignClient authFeignClient;

	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate)
			throws TokenValidationFailedException {

		if (!isValidSession(token)) {
			return null;
		}

		log.info("Start");
		List<RepSchedule> repSchedules = new ArrayList<>();

		List<Doctor> doctors = CsvParseUtil.parseDoctors(token);
		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeRepository.findAll();

		LocalDate localDate = scheduleStartDate;

		LocalTime now = LocalTime.now();
		LocalTime one = LocalTime.of(13, 0);

		LocalDate today = LocalDate.now();
		if (scheduleStartDate.isBefore(today)) {
			return repSchedules;
		}

		if (scheduleStartDate.equals(today)) {

			if (now.isAfter(one)) {
				localDate = localDate.plusDays(1);
			}

		}

		for (int i = 0; i < doctors.size(); i++) {

			if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				localDate = localDate.plusDays(1);
			}

			Doctor doctor = doctors.get(i);
			MedicalRepresentative medicalRepresentative = medicalRepresentatives.get(i % 3);

			RepSchedule repSchedule = new RepSchedule();
			repSchedule.setName(medicalRepresentative.getName());
			repSchedule.setDoctorName(doctor.getName());
			repSchedule.setDoctorContactNumber(doctor.getContactNumber());
			repSchedule.setMeetingDate(localDate);
			repSchedule.setMeetingSlot("1 PM to 2 PM");

			String[] medicinesByTreatingAilment = medicineStockClient
					.getMedicinesByTreatingAilment(token,doctor.getTreatingAilment());

			repSchedule.setMedicines(String.join(", ", medicinesByTreatingAilment));

			log.debug("repSchedule : {}", repSchedule);

			repSchedules.add(repSchedule);

			localDate = localDate.plusDays(1);
		}

		log.debug("repSchedules : {}", repSchedules);

		log.info("End");
		return repSchedules;
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}

		return true;
	}

}
