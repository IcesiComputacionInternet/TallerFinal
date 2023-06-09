package co.icesi.automoviles.integration;

import co.icesi.automoviles.TestConfigurationData;
import co.icesi.automoviles.api.CategoryAPI;
import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.CategoryShowDTO;
import co.icesi.automoviles.dto.LoginDTO;
import co.icesi.automoviles.dto.TokenDTO;
import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.exception.ShopError;
import co.icesi.automoviles.error.exception.ShopErrorDetail;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static co.icesi.automoviles.utils.DTOBuilder.defaultCategoryCreateDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    TokenDTO tokenDTO;

    @Autowired
    CategoryRepository categoryRepository;

    private TokenDTO login(String username, String password) throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO(username,password))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
        assertNotNull(token);
        return token;
    }

    private void loginAsAdmin() throws Exception {
        tokenDTO = login("johndoe1@email.com", "password");
    }

    private void loginAsUser() throws Exception {
        tokenDTO = login("johndoe2@email.com", "password");
    }

    private void loginAsShop() throws Exception {
        tokenDTO = login("johndoe3@email.com", "password");
    }

    @Test
    public void testEndpointForCategoryCreationWithAdminCredentials() throws Exception {
        loginAsAdmin();
        CategoryCreateDTO categoryCreateDTO = defaultCategoryCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(CategoryAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(categoryCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        CategoryShowDTO categoryShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CategoryShowDTO.class);
        assertNotNull(categoryShowDTO.getCategoryId());
        assertEquals(categoryCreateDTO.getName(), categoryShowDTO.getName());
        assertEquals(categoryCreateDTO.getDescription(), categoryShowDTO.getDescription());
        assertEquals(0, categoryShowDTO.getItems().size());
        Optional<Category> category = categoryRepository.findById(categoryShowDTO.getCategoryId());
        if(category.isEmpty()){
            fail();
        }
        categoryRepository.delete(category.get());
    }

    @Test
    public void testEndpointForCategoryCreationWithUserCredentials() throws Exception {
        loginAsUser();
        CategoryCreateDTO categoryCreateDTO = defaultCategoryCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(CategoryAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(categoryCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testEndpointForCategoryCreationWithShopCredentials() throws Exception {
        loginAsShop();
        CategoryCreateDTO categoryCreateDTO = defaultCategoryCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(CategoryAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(categoryCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testEndpointForCategoryCreationWithAdminCredentialsWhenTheNumberOfTheNewCategoryAlreadyExists() throws Exception {
        loginAsAdmin();
        CategoryCreateDTO categoryCreateDTO = defaultCategoryCreateDTO();
        categoryCreateDTO.setName("Sport");
        var result = mockMvc.perform(MockMvcRequestBuilders.post(CategoryAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(categoryCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        ShopError shopError = objectMapper.readValue(result.getResponse().getContentAsString(), ShopError.class);
        List<ShopErrorDetail> icesiErrorDetailList = shopError.getDetails();
        assertEquals(HttpStatus.BAD_REQUEST, shopError.getStatus());
        assertEquals(icesiErrorDetailList.size(), 1);
        assertEquals(ErrorCode.ERR_400.getCode(), icesiErrorDetailList.get(0).getErrorCode());
        assertEquals("field name: There is already a category with the name " + categoryCreateDTO.getName(), icesiErrorDetailList.get(0).getErrorMessage());
    }


    @Test
    public void testEndpointForCategoryUpdateWithAdminCredentials() throws Exception {
        loginAsAdmin();
        CategoryCreateDTO categoryCreateDTO = defaultCategoryCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(CategoryAPI.ROOT_PATH+"/246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(categoryCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        CategoryShowDTO categoryShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CategoryShowDTO.class);
        assertEquals("246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae", categoryShowDTO.getCategoryId().toString());
        assertEquals(categoryCreateDTO.getName(), categoryShowDTO.getName());
        assertEquals(categoryCreateDTO.getDescription(), categoryShowDTO.getDescription());
        assertEquals(8, categoryShowDTO.getItems().size());
        Optional<Category> category = categoryRepository.findById(UUID.fromString("246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae"));
        if(category.isEmpty()){
            fail();
        }
        Category originalCategory = Category.builder()
                .categoryId(UUID.fromString("246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae"))
                .name("Sport")
                .description("Sport category")
                .items(category.get().getItems())
                .build();
        categoryRepository.save(originalCategory);
    }

    @Test
    public void testEndpointForCategoryUpdateWithUserCredentials() throws Exception {
        loginAsUser();
        CategoryCreateDTO categoryCreateDTO = defaultCategoryCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(CategoryAPI.ROOT_PATH+"/246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(categoryCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testEndpointForCategoryUpdateWithShopCredentials() throws Exception {
        loginAsShop();
        CategoryCreateDTO categoryCreateDTO = defaultCategoryCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(CategoryAPI.ROOT_PATH+"/246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(categoryCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testEndpointForCategoryUpdateWithAdminCredentialsWhenTheCategoryIdIsNotFound() throws Exception {
        loginAsAdmin();
        CategoryCreateDTO categoryCreateDTO = defaultCategoryCreateDTO();
        categoryCreateDTO.setName("Sport");
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(CategoryAPI.ROOT_PATH+"/346ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(categoryCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        ShopError shopError = objectMapper.readValue(result.getResponse().getContentAsString(), ShopError.class);
        List<ShopErrorDetail> icesiErrorDetailList = shopError.getDetails();
        assertEquals(HttpStatus.NOT_FOUND, shopError.getStatus());
        assertEquals(icesiErrorDetailList.size(), 1);
        assertEquals(ErrorCode.ERR_404.getCode(), icesiErrorDetailList.get(0).getErrorCode());
        assertEquals("category with id: 346ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae not found", icesiErrorDetailList.get(0).getErrorMessage());

    }
}
