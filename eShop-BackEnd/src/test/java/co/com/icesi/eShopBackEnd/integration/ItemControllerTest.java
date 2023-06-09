package co.com.icesi.eShopBackEnd.integration;

import co.com.icesi.eShopBackEnd.integration.config.TestConfigurationData;
import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
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
public class ItemControllerTest {
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
    public void testCreateItemWhenCategoryNotExist() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/item").content(
                                objectMapper.writeValueAsString(defaultItemDto())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
            assertEquals(400, newResult.getResponse().getStatus());

    }


    @Test
    @Order(2)
    public void testCreateItem() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/item").content(
                                objectMapper.writeValueAsString(defaultItemDtoOk())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, newResult.getResponse().getStatus());

    }
    @Test
    @Order(3)
    public void testCreateItemWhenItemAlReadyExist() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/item").content(
                                objectMapper.writeValueAsString(defaultItemDtoOk())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(400, newResult.getResponse().getStatus());

    }
    private CreateItemDTO defaultItemDto() {
        return CreateItemDTO.builder().name("TV").price(2000L).category("TV").brand("Samsung").stock(3).imageURL("tv.com").description("Television HD").build();
    }
    private CreateItemDTO defaultItemDtoOk() {
        return CreateItemDTO.builder().name("TV").price(2000L).category("Television").brand("Samsung").stock(3).imageURL("tv.com").description("Television HD").build();
    }
}
