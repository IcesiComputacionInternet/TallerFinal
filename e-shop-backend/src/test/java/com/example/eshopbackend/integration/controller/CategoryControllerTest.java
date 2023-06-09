package com.example.eshopbackend.integration.controller;
import com.example.eshopbackend.TestConfigurationData;
import com.example.eshopbackend.dto.CategoryDTO;
import com.example.eshopbackend.dto.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestConfigurationData.class)
public class CategoryControllerTest{
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

    @Test
    public void testCreateCategory() throws Exception {
        loginAsAdmin();
        var result = mvc.perform(MockMvcRequestBuilders.post("/categories/add").content(
                                mapper.writeValueAsString(
                                        CategoryDTO.builder()
                                                .description("Category for testing")
                                                .name("Test Category")
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testGetAllCategories() throws Exception {
        loginAsAdmin();
        var result = mvc.perform(MockMvcRequestBuilders.get("/categories/getAll")
                        .header("Authorization", "Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    private void loginAsAdmin() throws Exception {
        token = getToken("johndoe@gmail.com", "password");
    }

    private void loginAsUser() throws Exception {
        token = getToken("johndoe2@email.com", "password");
    }

    private void loginAsShop() throws Exception {
        token = getToken("johndoe3@email.com", "password");
    }

}
