package co.com.icesi.eShopBackEnd.integration;

import co.com.icesi.eShopBackEnd.integration.config.TestConfigurationData;
import co.com.icesi.eShopBackEnd.dto.AssignCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
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
public class CategoryControllerTest {
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
    @Order(1)
    public void testCreateCategory() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/category").content(
                                objectMapper.writeValueAsString(defaultCategoryDTO())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, newResult.getResponse().getStatus());

    }
    @Test
    @Order(2)
    public void testCreateCategoryWhenAllReadyExist() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/category").content(
                                objectMapper.writeValueAsString(defaultCategoryDTO())
                        )

                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(400, newResult.getResponse().getStatus());

    }

    @Test
    public void testAssignCategoryNoExistCategory() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.patch("/category/assignCategory/").content(
                                objectMapper.writeValueAsString(assignCategoryNoExistDTO())
                        )

                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(404, newResult.getResponse().getStatus());

    }

    @Test
    public void testAssignCategoryButItemNotFound() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.patch("/category/assignCategory/").content(
                                objectMapper.writeValueAsString(assignCategoryNotFound())
                        )

                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(404, newResult.getResponse().getStatus());

    }

    @Test
    public void testAssignCategory() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.patch("/category/assignCategory/").content(
                                objectMapper.writeValueAsString(assignCategory())
                        )

                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, newResult.getResponse().getStatus());

    }
    private CreateCategoryDTO defaultCategoryDTO() {
        return CreateCategoryDTO.builder()
                .name("Teacher")
                .description("Is a teacher the university Icesi")
                .build();
    }
    public AssignCategoryDTO assignCategoryNoExistDTO(){
        return AssignCategoryDTO.builder()
                .itemName("Teacher")
                .category("No exist")
                .build();
    }

    public AssignCategoryDTO assignCategoryNotFound(){
        return AssignCategoryDTO.builder()
                .itemName("plasma")
                .category("TV")
                .itemName("itemValid")
                .build();
    }
    public AssignCategoryDTO assignCategory(){
        return AssignCategoryDTO.builder()
                .itemName("plasma")
                .category("Television")
                .itemName("itemValid")
                .build();
    }


}
