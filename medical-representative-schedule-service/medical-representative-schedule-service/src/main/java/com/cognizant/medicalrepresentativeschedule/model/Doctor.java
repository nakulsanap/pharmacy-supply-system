package com.cognizant.medicalrepresentativeschedule.model;

public class Doctor {
	private int id;
	private String name;
	private String contactNumber;
	private String treatingAilment;

	public Doctor(String name, String contactNumber, String treatingAilment) {
		super();
		this.name = name;
		this.contactNumber = contactNumber;
		this.treatingAilment = treatingAilment;
	}

	public Doctor(int id, String name, String contactNumber, String treatingAilment) {
		super();
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.treatingAilment = treatingAilment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getTreatingAilment() {
		return treatingAilment;
	}

	public void setTreatingAilment(String treatingAilment) {
		this.treatingAilment = treatingAilment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + ", treatingAilment="
				+ treatingAilment + "]";
	}

}
