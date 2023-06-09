package com.peliculas.grupo3.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculas.grupo3.TestConfigurationData;
import com.peliculas.grupo3.dto.LoginDTO;
import com.peliculas.grupo3.dto.TokenDTO;
import com.peliculas.grupo3.dto.UserDTO;
import com.peliculas.grupo3.dto.response.UserResponseDTO;
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
public class UserTest {

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllUsers() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/users/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();

        UserResponseDTO[] users = objectMapper.readValue(result.getResponse().getContentAsString(), UserResponseDTO[].class);
        assertNotNull(users);
        assertEquals(2, users.length);
    }

    @Test
    public void createUserHappyPath() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/users/").content(
                                objectMapper.writeValueAsString(new UserDTO(
                                        "nombre",
                                        "apellido",
                                        "123@gmail.co",
                                        "+57 305 123 432",
                                        "123",
                                        "USER",
                                "address",
                                        "10/10/200"
                                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk());
        UserResponseDTO userResponseDTO = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserResponseDTO.class);
        assertNotNull(userResponseDTO);
        assertEquals("nombre", userResponseDTO.getFirstName());
    }

    @Test
    public void createUserBadEmail() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        mocMvc.perform(MockMvcRequestBuilders.post("/users/").content(
                                objectMapper.writeValueAsString(new UserDTO(
                                        "nombre",
                                        "apellido",
                                        "123gmail",
                                        "+57 305 125 432",
                                        "123",
                                        "USER",
                                        "address",
                                        "10/10/200")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserBadPhone() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        mocMvc.perform(MockMvcRequestBuilders.post("/users/").content(
                                objectMapper.writeValueAsString(new UserDTO(
                                        "nombre",
                                        "apellido",
                                        "123@gmail.com",
                                        "305 125 432",
                                        "123",
                                        "USER",
                                        "address",
                                        "10/10/200")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserTakenEmail() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result=mocMvc.perform(MockMvcRequestBuilders.post("/users/").content(
                                objectMapper.writeValueAsString(new UserDTO(
                                        "nombre",
                                        "apellido",
                                        "noname@email.com",
                                        "+57 305 125 432",
                                        "123",
                                        "USER",
                                        "address",
                                        "10/10/200")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError());
        MovieError movieError = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("Ya hay un usuario con este email", movieError.getDetails());
    }

    @Test
    public void createUserTakenPhone() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result=mocMvc.perform(MockMvcRequestBuilders.post("/users/").content(
                                objectMapper.writeValueAsString(new UserDTO(
                                        "nombre",
                                        "apellido",
                                        "new@email.com",
                                        "+57123123123",
                                        "123",
                                        "not a role",
                                        "address",
                                        "10/10/200")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError());
        MovieError movieError = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("Ya hay un usuario con este celular", movieError.getDetails());
    }

    @Test
    public void findUserHappyPath() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        String name="he who";
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/users/findByName/"+name)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        UserResponseDTO UserResponseDTO = objectMapper.readValue(result.getResponse().getContentAsString(), UserResponseDTO.class);
        assertNotNull(UserResponseDTO);
        assertEquals("he who", UserResponseDTO.getFirstName());

    }

    @Test
    public void findUserNotValid() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        String name= "not a user";
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/users/findByName/"+name)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isNotFound())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("USER_NOT_FOUND", movieError.getDetails());

    }

}
