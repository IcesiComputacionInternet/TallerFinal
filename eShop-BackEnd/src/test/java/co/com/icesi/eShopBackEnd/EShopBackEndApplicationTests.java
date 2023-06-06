package co.com.icesi.eShopBackEnd;

import co.com.icesi.eShopBackEnd.dto.LoginDTO;
import co.com.icesi.eShopBackEnd.dto.response.TokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
class EShopBackEndApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}






	@Test
	public void testTokenEndPoint() throws Exception {
		var result = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(
								objectMapper.writeValueAsString(new LoginDTO("jhonDoe@email.com", "password")))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		TokenDTO token = objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
		assertNotNull(token);
	}





	@Test
	public void testTokenEndPointWithValidEmail() throws Exception {
		var result = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(
								objectMapper.writeValueAsString(new LoginDTO("jhonDoe@email.com", "password")))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

	}




	@Test
	public void testTokenEndPointWithInValidEmail() throws Exception {
		var result = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(
								objectMapper.writeValueAsString(new LoginDTO("jhondoe@email.com", "password")))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andReturn();

	}
}

