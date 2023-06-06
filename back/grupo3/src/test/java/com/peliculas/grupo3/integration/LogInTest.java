package com.peliculas.grupo3.integration;


import com.peliculas.grupo3.TestConfigurationData;
import com.peliculas.grupo3.dto.TokenDTO;
import com.peliculas.grupo3.dto.LoginDTO;
import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.error.exception.MovieError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
class LogInTest {

	@Autowired
	private MockMvc mocMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testTokenEndpointHappyPath() throws Exception {
		var result = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
								objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
						)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		TokenDTO tokenDTO = objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
		assertNotNull(tokenDTO);
	}

	@Test
	public void testTokenEndpointInvalidUser() throws Exception {
		var result = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
								objectMapper.writeValueAsString(new LoginDTO("yesname@email.com", "password"))
						)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andReturn();

		MovieError error = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
		assertNotNull(error);
	}

	@Test
	public void testTokenEndpointPassword() throws Exception {
		var result = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
								objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "NotApassword"))
						)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError())
				.andReturn();

		MovieError error = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
		assertNotNull(error);
		assertEquals("Bad credentials", error.getDetails());
	}

	@Test
	public void testCurrentUser() throws Exception {
		String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
								objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
						)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
		var result = mocMvc.perform(MockMvcRequestBuilders.get("/users/CurrentUser")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
				.andExpect(status().isOk())
				.andReturn();
		UserDTO userDTO = objectMapper.readValue(result.getResponse().getContentAsString(), UserDTO.class);
		assertNotNull(userDTO);
		assertEquals("noname@email.com", userDTO.getEmail());
	}


}

