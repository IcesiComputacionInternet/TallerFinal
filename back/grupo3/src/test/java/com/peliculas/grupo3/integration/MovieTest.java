package com.peliculas.grupo3.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculas.grupo3.TestConfigurationData;
import com.peliculas.grupo3.dto.LoginDTO;
import com.peliculas.grupo3.dto.MovieDTO;
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
class MovieTest {

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllMovies() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/movies/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        MovieDTO[] movieDTO = objectMapper.readValue(result.getResponse().getContentAsString(), MovieDTO[].class);
        assertNotNull(movieDTO);
    }

    @Test
    public void findMovieByNameHappyPath() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/movies/findByName")
                        .content("Fast X")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        MovieDTO movieDTO = objectMapper.readValue(result.getResponse().getContentAsString(), MovieDTO.class);
        assertEquals("Fast X", movieDTO.getName());
        assertNotNull(movieDTO);
    }

    @Test
    public void findMovieByNameNotFound() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/movies/findByName")
                        .content("Fast 10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertEquals("La pelicula no existe", movieError.getDetails());
    }

    @Test
    public void createMovieHappyPath() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/movies/")
                        .content(
                                objectMapper.writeValueAsString(
                                    MovieDTO.builder()
                                            .name("Dungeons & Dragons: Honor Among Thieves")
                                            .description("A charming thief and a band of unlikely adventurers undertake an epic heist to retrieve a lost relic, but things go dangerously awry when they run afoul of the wrong people.")
                                            .categoryName("Fantasia")
                                            .price(10L)
                                            .imageURL("https://image.tmdb.org/t/p/original/A7AoNT06aRAc4SV89Dwxj3EYAgC.jpg")
                                            .pgRating("PG-13")
                                            .build()
                                )
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        MovieDTO movieDTO = objectMapper.readValue(result.getResponse().getContentAsString(), MovieDTO.class);
        assertEquals("Dungeons & Dragons: Honor Among Thieves", movieDTO.getName());
    }

    @Test
    public void createMovieAlreadyExists() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/movies/")
                        .content(
                                objectMapper.writeValueAsString(
                                        MovieDTO.builder()
                                                .name("Fast X")
                                                .description("New movie")
                                                .categoryName("Fantasia")
                                                .price(10L)
                                                .imageURL("https://image.tmdb.org/t/p/original/A7AoNT06aRAc4SV89Dwxj3EYAgC.jpg")
                                                .pgRating("PG-13")
                                                .build()
                                )
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertEquals("La pelicula ya existe", movieError.getDetails());
    }

    @Test
    public void createMovieNonExistingCategory() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/movies/")
                        .content(
                                objectMapper.writeValueAsString(
                                        MovieDTO.builder()
                                                .name("New movie")
                                                .description("new details")
                                                .categoryName("not a category")
                                                .price(10L)
                                                .imageURL("https://i.imgflip.com/5b93jb.png")
                                                .pgRating("PG-13")
                                                .build()
                                )
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertEquals("La categoria no existe", movieError.getDetails());
    }

    @Test
    public void createMovieNonExistingRating() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/movies/")
                        .content(
                                objectMapper.writeValueAsString(
                                        MovieDTO.builder()
                                                .name("New movie")
                                                .description("new details")
                                                .categoryName("Fantasia")
                                                .price(10L)
                                                .imageURL("https://i.imgflip.com/5b93jb.png")
                                                .pgRating("PG-9999")
                                                .build()
                                )
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertEquals("El rating no es valido", movieError.getDetails());
    }

    @Test
    public void createMovieWrongPrice() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.post("/movies/")
                        .content(
                                objectMapper.writeValueAsString(
                                        MovieDTO.builder()
                                                .name("New movie")
                                                .description("new details")
                                                .categoryName("Fantasia")
                                                .price(-10L)
                                                .imageURL("https://i.imgflip.com/5b93jb.png")
                                                .pgRating("PG-13")
                                                .build()
                                )
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertEquals("El precio debe ser mayor a 0", movieError.getDetails());
    }

    @Test
    public void getMovieByCategoryHappyPath() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/movies/findByCategory")
                        .content("Accion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        MovieDTO[] movies = objectMapper.readValue(result.getResponse().getContentAsString(), MovieDTO[].class);
        assertNotNull(movies);
    }

    @Test
    public void getMovieByCategoryNotValid() throws Exception {
        String token = mocMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("noname@email.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TokenDTO tokenDTO = objectMapper.readValue(token, TokenDTO.class);
        var result = mocMvc.perform(MockMvcRequestBuilders.get("/movies/findByCategory")
                        .content("a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken()))
                .andExpect(status().is5xxServerError())
                .andReturn();
        MovieError movieError = objectMapper.readValue(result.getResponse().getContentAsString(), MovieError.class);
        assertEquals("La categoria no existe", movieError.getDetails());
    }

}
