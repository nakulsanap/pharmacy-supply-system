package com.cognizant.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.pharmacysupply.model.JwtResponse;

/**
 * This is Authorization Feign client which is used to communicate with the
 * Authorization Micro Service. The verify token method is used to validate
 * whether the request if from valid user or not.
 */
@FeignClient(name = "authorization-service", url = "http://localhost:8084")
public interface AuthenticationFeignClient {

	@RequestMapping(value = "/api/auth/validate" ,method = RequestMethod.GET)
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);
	
}
