package com.cognizant.medicalrepresentativeschedule.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Doctor {
	
	private int id;
	private String name;
	private String contactNumber;
	private String treatingAilment;
	
}
