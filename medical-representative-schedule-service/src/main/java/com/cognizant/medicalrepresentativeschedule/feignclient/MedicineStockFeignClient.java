package com.cognizant.medicalrepresentativeschedule.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * This interface communicates with medicine-stock-service to get Medicines based on Treating Ailment.
 * 
 */
@FeignClient(name = "medicine-stock-service", url = "http://localhost:8081")
public interface MedicineStockFeignClient {

	@PostMapping("/api/medicine-stock/byTreatingAilment/{treatingAilment}")
	public String[] getMedicinesByTreatingAilment(@RequestHeader("Authorization") String token, @PathVariable("treatingAilment") String treatingAilment);

}
