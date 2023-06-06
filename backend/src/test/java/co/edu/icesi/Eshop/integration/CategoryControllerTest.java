package co.edu.icesi.Eshop.integration;

import co.edu.icesi.Eshop.TestConfigurationData;
import co.edu.icesi.Eshop.dto.CategoryDTO;
import co.edu.icesi.Eshop.dto.LoginDTO;
import co.edu.icesi.Eshop.dto.TokenDTO;
import co.edu.icesi.Eshop.error.exception.EShopError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
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

import static co.edu.icesi.Eshop.api.CategoryAPI.BASE_CATEGORY_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    public TokenDTO generateAdminToken() throws Exception{
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("johndoe@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
    }

    public TokenDTO generateShopToken() throws Exception{
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("johndoe2@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
    }

    public TokenDTO generateUserToken() throws Exception{
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("johndoe3@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
    }

    @Nested
    public class testsCreateCategoryHappyPath{

        @Test
        public void testCreateCategoryFromAdmin() throws Exception{
            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_CATEGORY_URL).content(
                                    objectMapper.writeValueAsString(defaultCategory())
                            )
                            .header("Authorization","Bearer "+generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            CategoryDTO categoryDTO = objectMapper.readValue(newResult.getResponse().getContentAsString(), CategoryDTO.class);
            assertNotNull(categoryDTO);
            assertEquals(categoryDTO.getName(),"Example category");
        }
    }

    @Nested
    public class testsCreateCategoryNotHappyPath{

        @Test
        public void testCreateCategoryWithNameAlreadyExists() throws Exception{

            CategoryDTO categoryDTO = defaultCategory();
            categoryDTO.setName("Cuidado del hogar");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_CATEGORY_URL).content(
                                    objectMapper.writeValueAsString(categoryDTO)
                            )
                            .header("Authorization","Bearer "+generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isConflict())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(),EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("Category with name "+categoryDTO.getName()+" already exists",detail.getErrorMessage());
            assertEquals(409, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateCategoryFromShop() throws Exception{

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_CATEGORY_URL).content(
                                    objectMapper.writeValueAsString(defaultCategory())
                            )
                            .header("Authorization","Bearer "+generateShopToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden())
                    .andReturn();

            assertEquals(403, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateCategoryFromUser() throws Exception{

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_CATEGORY_URL).content(
                                    objectMapper.writeValueAsString(defaultCategory())
                            )
                            .header("Authorization","Bearer "+generateUserToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden())
                    .andReturn();

            assertEquals(403, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateCategoryWithMissingName() throws Exception{

            CategoryDTO categoryDTO = defaultCategory();
            categoryDTO.setName("");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_CATEGORY_URL).content(
                                    objectMapper.writeValueAsString(categoryDTO)
                            )
                            .header("Authorization","Bearer "+generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("name is missing", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }
    }

    private CategoryDTO defaultCategory(){
        return CategoryDTO.builder()
                .name("Example category")
                .description("A example category")
                .build();
    }
}
