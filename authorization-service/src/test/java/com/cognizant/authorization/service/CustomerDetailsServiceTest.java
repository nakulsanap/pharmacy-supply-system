
package com.cognizant.authorization.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.authorization.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDetailsServiceTest {

	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@Test
	public void testLoadUserByUsername() {
		log.info("Start");
		
		UserDetails userDetails = customerDetailsService.loadUserByUsername("root");
		assertEquals("root", userDetails.getUsername());
		
		log.info("End");
	}

	@Test(expected = UserNotFoundException.class)
	public void testLoadUserByUsername_Exception() {
		log.info("Start");
		
		customerDetailsService.loadUserByUsername("admin");
		
		log.info("End");
	}
	
	
}