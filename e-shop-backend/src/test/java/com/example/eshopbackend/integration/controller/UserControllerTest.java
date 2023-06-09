package com.example.eshopbackend.integration.controller;

import com.example.eshopbackend.TestConfigurationData;
import com.example.eshopbackend.dto.LoginDTO;
import com.example.eshopbackend.dto.RoleDTO;
import com.example.eshopbackend.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestConfigurationData.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    private String token;

    private String getToken(String username, String password) throws Exception {
        var result = mvc.perform(MockMvcRequestBuilders.post("/token").content(
                                mapper.writeValueAsString(new LoginDTO(username,password))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        JSONObject json = new JSONObject((result.getResponse().getContentAsString()));
        return json.getString("token");
    }

    private void loginAsAdmin() throws Exception {
        token = getToken("johndoe@gmail.com", "password");
    }

    private void loginAsUser() throws Exception {
        token = getToken("juanpalta@hotmail.com", "password");
    }

    private void loginAsBank() throws Exception {
        token = getToken("johndoe3@email.com", "password");
    }

    @Test
    public void testCreateAUserHappy_Path() throws Exception {
        loginAsAdmin();
        var result = mvc.perform(MockMvcRequestBuilders.post("/users/add").content(
                                mapper.writeValueAsString(
                                        UserDTO.builder()
                                                .firstName("name")
                                                .lastName("lastname")
                                                .address("address")
                                                .birthDate(LocalDateTime.now())
                                                .phoneNumber("123456789")
                                                .email("email@hotmail.com")
                                                .password(new BCryptPasswordEncoder().encode("password"))
                                                .role(RoleDTO.builder().roleId(("09ca0952-b5a7-4998-b6f5-0628002b519b")).roleName("ADMIN").description("Role for demo").build())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateAUserIncorrect_Path() throws Exception {
        loginAsUser();
        var result = mvc.perform(MockMvcRequestBuilders.post("/users/add").content(
                                mapper.writeValueAsString(
                                        UserDTO.builder()
                                                .firstName("name")
                                                .lastName("lastname")
                                                .address("address")
                                                .birthDate(LocalDateTime.now())
                                                .phoneNumber("123456789")
                                                .email("email@hotmail.com")
                                                .password(new BCryptPasswordEncoder().encode("password"))
                                                .role(RoleDTO.builder().roleId(("09ca0952-b5a7-4998-b6f5-0628002b519b")).roleName("ADMIN").description("Role for demo").build())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
    System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        loginAsAdmin();
        var result = mvc.perform(MockMvcRequestBuilders.get("/users/get/all")
                        .header("Authorization", "Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }


}
