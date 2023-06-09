package co.icesi.automoviles.integration;

import co.icesi.automoviles.TestConfigurationData;
import co.icesi.automoviles.api.CategoryAPI;
import co.icesi.automoviles.api.RoleAPI;
import co.icesi.automoviles.dto.*;
import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.exception.ShopError;
import co.icesi.automoviles.error.exception.ShopErrorDetail;
import co.icesi.automoviles.repository.CategoryRepository;
import co.icesi.automoviles.repository.RoleRepository;
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

import java.util.List;

import static co.icesi.automoviles.utils.DTOBuilder.defaultCategoryCreateDTO;
import static co.icesi.automoviles.utils.DTOBuilder.defaultRoleCreateDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
public class RoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    TokenDTO tokenDTO;

    @Autowired
    RoleRepository roleRepository;

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
    public void testEndpointForRoleCreationWithAdminCredentials() throws Exception {
        loginAsAdmin();
        RoleCreateDTO roleCreateDTO = defaultRoleCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(RoleAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(roleCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RoleShowDTO roleShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), RoleShowDTO.class);
        assertNotNull(roleShowDTO.getRoleId());
        assertEquals(roleCreateDTO.getRoleName(), roleShowDTO.getRoleName());
        assertEquals(roleCreateDTO.getDescription(), roleShowDTO.getDescription());
    }

    @Test
    public void testEndpointForRoleCreationWithAdminCredentialsWhenTheNameIsRepeated() throws Exception {
        loginAsAdmin();
        RoleCreateDTO roleCreateDTO = defaultRoleCreateDTO();
        roleCreateDTO.setRoleName("USER");
        var result = mockMvc.perform(MockMvcRequestBuilders.post(RoleAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(roleCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        ShopError shopError = objectMapper.readValue(result.getResponse().getContentAsString(), ShopError.class);
        List<ShopErrorDetail> icesiErrorDetailList = shopError.getDetails();
        assertEquals(HttpStatus.BAD_REQUEST, shopError.getStatus());
        assertEquals(icesiErrorDetailList.size(), 1);
        assertEquals(ErrorCode.ERR_400.getCode(), icesiErrorDetailList.get(0).getErrorCode());
        assertEquals("field name: There is already a role with the name " + roleCreateDTO.getRoleName(), icesiErrorDetailList.get(0).getErrorMessage());
    }

    @Test
    public void testEndpointForRoleCreationWithUserCredentials() throws Exception {
        loginAsUser();
        RoleCreateDTO roleCreateDTO = defaultRoleCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(RoleAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(roleCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testEndpointForRoleCreationWithShopCredentials() throws Exception {
        loginAsShop();
        RoleCreateDTO roleCreateDTO = defaultRoleCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(RoleAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(roleCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }
}
