package com.cognizant.medicalrepresentativeschedule.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="medicine-stock-microservice", url="http://localhost:8080")  
public interface MedicineStockClient {

	@GetMapping("/{treatingAilment}")
	public String[] getMedicinesByTreatingAilment(@RequestHeader("Authorization") String token,@PathVariable("treatingAilment") String treatingAilment);
	
}
