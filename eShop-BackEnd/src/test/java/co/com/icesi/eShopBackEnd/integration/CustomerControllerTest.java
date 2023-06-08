package co.com.icesi.eShopBackEnd.integration;


import co.com.icesi.eShopBackEnd.TestConfigurationData;
import co.com.icesi.eShopBackEnd.dto.AssignCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCustomerDTO;
import co.com.icesi.eShopBackEnd.dto.LoginDTO;
import co.com.icesi.eShopBackEnd.dto.response.TokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    public TokenDTO generateAdminToken() throws Exception{
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(
                                objectMapper.writeValueAsString(new LoginDTO("jhonDoe@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
    }
    @Test
    public void testCreateCategory() throws Exception{
        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/user").content(
                                objectMapper.writeValueAsString(createCustomerDTO())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, newResult.getResponse().getStatus());

    }
    @Test
    public void testCreateCategoryWhenPhoneNumberAllReadyExist() throws Exception{
        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/user").content(
                                objectMapper.writeValueAsString(createCustomerDTOWhenPhoneNumberAllReadyExist())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400, newResult.getResponse().getStatus());

    }

    @Test
    public void testCreateCategoryWhenEmailAllReadyExist() throws Exception{
        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/user").content(
                                objectMapper.writeValueAsString(createCustomerDTOWhenEmailAllReadyExist())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400, newResult.getResponse().getStatus());

    }
    @Test
    public void testCreateCategoryWhenEmailFormatInvalid() throws Exception{
        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/user").content(
                                objectMapper.writeValueAsString(createCustomerDTOWhenEmailFormatInvalid())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400, newResult.getResponse().getStatus());

    }
    @Test
    public void testCreateCategoryWhenPhoneNumberFormatInvalid() throws Exception{
        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/user").content(
                                objectMapper.writeValueAsString(createCustomerDTOWhenPhoneNumberFormatInvalid())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400, newResult.getResponse().getStatus());

    }
    private CreateCustomerDTO createCustomerDTO() {
        return CreateCustomerDTO.builder()
                .firstName("luis")
                .lastName("andres")
                .address("call 65 Nº 32")
                .birthday("2023-06-06")
                .email("johndo@eemail.com")
                .phoneNumber("+573258690188")
                .password("password").build();
    }
    private CreateCustomerDTO createCustomerDTOWhenPhoneNumberAllReadyExist() {
        return CreateCustomerDTO.builder()
                .firstName("luis")
                .lastName("andres")
                .address("call 65 Nº 32")
                .birthday("2023-06-06")
                .email("johndoe@email.com")
                .phoneNumber("+573258690127")
                .password("password").build();
    }
    private CreateCustomerDTO createCustomerDTOWhenEmailAllReadyExist() {
        return CreateCustomerDTO.builder()
                .firstName("luis")
                .lastName("andres")
                .address("call 65 Nº 32")
                .birthday("2023-06-06")
                .email("jhonDoe@email.com")
                .phoneNumber("+573258690as27")
                .password("password").build();
    }
    private CreateCustomerDTO createCustomerDTOWhenEmailFormatInvalid() {
        return CreateCustomerDTO.builder()
                .firstName("luis")
                .lastName("andres")
                .address("call 65 Nº 32")
                .birthday("2023-06-06")
                .email("json Doe email.com")
                .phoneNumber("+573258690as27")
                .password("password").build();
    }
    private CreateCustomerDTO createCustomerDTOWhenPhoneNumberFormatInvalid() {
        return CreateCustomerDTO.builder()
                .firstName("luis")
                .lastName("andres")
                .address("call 65 Nº 32")
                .birthday("2023-06-06")
                .email("jhonDoe@email.com")
                .phoneNumber("3258690as27")
                .password("password").build();
    }



}
