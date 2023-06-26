package com.cognizant.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pharmacysupply.model.MedicineStock;

/**
 * This is Medicine Stock Feign Client which is used to communicate with
 * medicine stock micro service to get medicine stock.
 *
 */
@FeignClient(url = "http://localhost:8081", name = "medicine-stock-service")
public interface MedicineStockFeignClient {


	@PostMapping("/api/medicine-stock/get-stock-count/{medicine}")
	public MedicineStock getNumberOfTabletsInStockByName(@RequestHeader(name = "Authorization") String token,
			@PathVariable("medicine") String medicine);


	@PostMapping("/api/medicine-stock/update-stock/{medicine}/{count}")
	public Boolean updateNumberOfTabletsInStockByName(@RequestHeader(name = "Authorization") String token,
			@PathVariable("medicine") String medicine, @PathVariable("count") int count);
}
