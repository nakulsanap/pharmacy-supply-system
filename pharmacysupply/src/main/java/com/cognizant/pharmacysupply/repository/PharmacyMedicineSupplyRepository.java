package com.cognizant.pharmacysupply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.pharmacysupply.model.MedicineSupply;

@Repository
public interface PharmacyMedicineSupplyRepository extends JpaRepository<MedicineSupply, Integer>{

}
