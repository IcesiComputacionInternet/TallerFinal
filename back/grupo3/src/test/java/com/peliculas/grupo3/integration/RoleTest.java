package com.peliculas.grupo3.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculas.grupo3.TestConfigurationData;
import com.peliculas.grupo3.dto.LoginDTO;
import com.peliculas.grupo3.dto.RoleDTO;
import com.peliculas.grupo3.dto.TokenDTO;
import com.peliculas.grupo3.error.exception.MovieError;
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
public class RoleTest {

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllRoles() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/roles/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        RoleDTO[] roles = objectMapper.readValue(result.getResponse().getContentAsString(), RoleDTO[].class);
        assertNotNull(roles);
    }

    @Test
    public void createRoleHappyPath() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/roles/").content(
                                objectMapper.writeValueAsString(new RoleDTO(
                                        "TEST",
                                        "rol de prueba"

                                )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk());
        RoleDTO roleDTO = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), RoleDTO.class);
        assertNotNull(roleDTO);
        assertEquals("rol de prueba", roleDTO.getDescription());
    }

    @Test
    public void createRoleAlreadyExists() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/roles/").content(
                                objectMapper.writeValueAsString(new RoleDTO(
                                        "ADMIN",
                                        "rol de prueba"

                                )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError());
        MovieError movieError = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("El rol ya existe", movieError.getDetails());
    }
}
