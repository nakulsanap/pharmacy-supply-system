package com.cognizant.pharmacysupply.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This is a model class which is used as a table using Spring Data
 * JPA's @Entity Annotation. This contains fields like id, pharmacy name,
 * medicine name and supply count
 */
@Entity
@Table(name = "medicine_supply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicineSupply {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;

	private String pharmacyName;

	private String medicineName;

	private int supplyCount;

}
