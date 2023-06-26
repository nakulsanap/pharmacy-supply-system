package com.cognizant.medicalrepresentativeschedule.model;

import java.time.LocalDate;

public class RepSchedule {

	private String name;
	private String doctorName;
	private String meetingSlot;
	private LocalDate meetingDate;
	private String doctorContactNumber;
	private String medicines;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getMeetingSlot() {
		return meetingSlot;
	}

	public void setMeetingSlot(String meetingSlot) {
		this.meetingSlot = meetingSlot;
	}

	public LocalDate getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getDoctorContactNumber() {
		return doctorContactNumber;
	}

	public void setDoctorContactNumber(String doctorContactNumber) {
		this.doctorContactNumber = doctorContactNumber;
	}

	public String getMedicines() {
		return medicines;
	}

	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}

	@Override
	public String toString() {
		return "RepSchedule [name=" + name + ", doctorName=" + doctorName + ", meetingSlot=" + meetingSlot
				+ ", meetingDate=" + meetingDate + ", doctorContactNumber=" + doctorContactNumber + ", medicines="
				+ medicines + "]";
	}

}
