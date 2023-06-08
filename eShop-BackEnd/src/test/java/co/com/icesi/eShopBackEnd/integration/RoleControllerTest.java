package co.com.icesi.eShopBackEnd.integration;

import co.com.icesi.eShopBackEnd.TestConfigurationData;
import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import co.com.icesi.eShopBackEnd.dto.LoginDTO;
import co.com.icesi.eShopBackEnd.dto.response.TokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
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
public class RoleControllerTest {
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

    public TokenDTO generateUserToken() throws Exception{
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(
                                objectMapper.writeValueAsString(new LoginDTO("z@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
    }

    public TokenDTO generateShopToken() throws Exception{
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(
                                objectMapper.writeValueAsString(new LoginDTO("zas@email.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
    }
    @Test
    public void testCreateRoleAdmin() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/role").content(
                                objectMapper.writeValueAsString(defaultRoleAdmin())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CreateRoleDTO role = objectMapper.readValue(newResult.getResponse().getContentAsString(), CreateRoleDTO.class);
        assertEquals(role.roleName(),"Admin");
    }

    @Test
    public void testCreateRoleShop() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/role").content(
                                objectMapper.writeValueAsString(defaultRoleShop())
                        )
                        .header("Authorization","Bearer "+generateAdminToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CreateRoleDTO role = objectMapper.readValue(newResult.getResponse().getContentAsString(), CreateRoleDTO.class);
        assertEquals(role.roleName(),"Shop");
    }
    @Test
    public void testCreateRoleUser() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/role").content(
                                objectMapper.writeValueAsString(defaultRoleUser())
                        )
                        .header("Authorization","Bearer "+generateUserToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CreateRoleDTO role = objectMapper.readValue(newResult.getResponse().getContentAsString(), CreateRoleDTO.class);
        assertEquals(role.roleName(),"User");
    }
    @Test
    public void testCreateRoleNameAllReadyExist() throws Exception{

        var newResult = mockMvc.perform(MockMvcRequestBuilders.post("/role").content(
                                objectMapper.writeValueAsString(defaultRoleUser())
                        )
                        .header("Authorization","Bearer "+generateUserToken().getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400, newResult.getResponse().getStatus());
    }
    public CreateRoleDTO defaultRoleAdmin(){
        return CreateRoleDTO.builder()
                .roleName("Admin")
                .description("Role for demo")
                .build();
    }
    public CreateRoleDTO defaultRoleUser(){
        return CreateRoleDTO.builder()
                .roleName("User")
                .description("Role for demo")
                .build();
    }
    public CreateRoleDTO defaultRoleShop(){
        return CreateRoleDTO.builder()
                .roleName("Shop")
                .description("Role for demo")
                .build();
    }

}
