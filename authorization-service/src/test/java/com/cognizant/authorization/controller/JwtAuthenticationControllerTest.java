
package com.cognizant.authorization.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.authorization.model.UserLoginCredential;
import com.cognizant.authorization.model.UserToken;
import com.cognizant.authorization.service.CustomerDetailsService;
import com.cognizant.authorization.service.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtAuthenticationControllerTest {

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private CustomerDetailsService customerDetailsService;

	private MockMvc mockMvc;

	@InjectMocks
	private JwtAuthenticationController jwtAuthenticationController;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(JwtAuthenticationController.class).build();
	}

	@Test
	public void testLogin() throws Exception {

		log.info("Start");

		when(jwtUtil.generateToken(Mockito.any(UserDetails.class))).thenReturn("token");
		when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))

				.thenReturn(null);

		ResponseEntity<UserToken> responseEntity = jwtAuthenticationController
				.login(new UserLoginCredential("root", "root"));

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("root", responseEntity.getBody().getUserid());
//		assertEquals("token", responseEntity.getBody().getAuthToken());

		log.info("End");
	}

	@Test
	public void testLogin_Exception() throws Exception {
		log.info("Start");

		jwtAuthenticationController.login(new UserLoginCredential("admin", "admin"));

		log.info("End");
	}

}





