package com.peliculas.grupo3.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculas.grupo3.TestConfigurationData;
import com.peliculas.grupo3.dto.*;
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
public class OrderTest {

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllOrders() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/orders/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        OrderDTO[] orderDTOS = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO[].class);
        assertNotNull(orderDTOS);
    }

    @Test
    public void getUserOrdersHappyPath() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        String email="noname@email.com";
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/orders/getUserOrders/"+email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        OrderDTO[] orderDTOS = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO[].class);
        assertNotNull(orderDTOS);
    }

    @Test
    public void getUserBadUser() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        String email="not an email@email.com";
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/orders/getUserOrders/"+email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("No existe un usuario con este email", movieError.getDetails());

    }

    @Test
    public void getUserOrdersNoOrders() throws Exception {
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
                                        "noOrders@gmail.co",
                                        "+57 399 123 432",
                                        "123",
                                        "USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk());
        String email="noOrders@gmail.co";
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/orders/getUserOrders/"+email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("El usuario no tiene ordenes", movieError.getDetails());

    }
    @Test
    public void findByNumberHappyPath() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        String number="1";
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/orders/findByNumber/"+number)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        OrderDTO orderDTO = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);
        assertNotNull(orderDTO);
        assertEquals("1", orderDTO.getOrderNumber());

    }

    @Test
    public void findByNumberBadOrderNumber() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        String number="3";
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/orders/findByNumber/"+number)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("No existe una orden con este numero", movieError.getDetails());

    }

    @Test
    public void addMovieHappyPath() throws Exception{
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/orders/addMovie")
                        .content(objectMapper.writeValueAsString( new OrderTargetDTO(
                                "2",
                                "John Wick: Chapter 4"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        OrderDTO orderDTO = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);
        assertNotNull(orderDTO);
    }

    @Test
    public void addMovieAlreadyInOrder() throws Exception{
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/orders/addMovie")
                        .content(objectMapper.writeValueAsString( new OrderTargetDTO(
                                "2",
                                "Fast X"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("La pelicula ya esta en la orden", movieError.getDetails());
    }

    @Test
    public void addMovieMovieNotFound() throws Exception{
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/orders/addMovie")
                        .content(objectMapper.writeValueAsString( new OrderTargetDTO(
                                "2",
                                "Fast 10"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("No existe una pelicula con este nombre", movieError.getDetails());
    }

    @Test
    public void addMovieOrderNotFound() throws Exception{
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/orders/addMovie")
                        .content(objectMapper.writeValueAsString( new OrderTargetDTO(
                                "3",
                                "Spider-Man: Across the Spider-Verse"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("No existe una orden con este numero", movieError.getDetails());
    }

    @Test
    public void deleteMovieHappyPath() throws Exception{
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/orders/deleteMovie")
                        .content(objectMapper.writeValueAsString( new OrderTargetDTO(
                                "2",
                                "Fast X"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        OrderDTO orderDTO = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);
        assertNotNull(orderDTO);
    }

    @Test
    public void deleteMovieNotInOrder() throws Exception{
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/orders/deleteMovie")
                        .content(objectMapper.writeValueAsString( new OrderTargetDTO(
                                "2",
                                "Spider-Man: Across the Spider-Verse"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("La orden no contiene esta pelicula", movieError.getDetails());
    }

    @Test
    public void deleteMovieMovieNotFound() throws Exception{
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/orders/deleteMovie")
                        .content(objectMapper.writeValueAsString( new OrderTargetDTO(
                                "2",
                                "Fast 10"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("No existe una pelicula con este nombre", movieError.getDetails());
    }

    @Test
    public void removeMovieOrderNotFound() throws Exception{
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/orders/deleteMovie")
                        .content(objectMapper.writeValueAsString( new OrderTargetDTO(
                                "3",
                                "Spider-Man: Across the Spider-Verse"
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertNotNull(movieError);
        assertEquals("No existe una orden con este numero", movieError.getDetails());
    }


}
