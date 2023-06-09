package co.icesi.automoviles.integration;

import co.icesi.automoviles.TestConfigurationData;
import co.icesi.automoviles.api.EShopUserAPI;
import co.icesi.automoviles.dto.EShopUserCreateDTO;
import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.dto.LoginDTO;
import co.icesi.automoviles.dto.TokenDTO;
import co.icesi.automoviles.enums.RoleType;
import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.exception.ShopError;
import co.icesi.automoviles.error.exception.ShopErrorDetail;
import co.icesi.automoviles.repository.EShopUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static co.icesi.automoviles.utils.DTOBuilder.defaultEShopUserCreateDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
public class EShopUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    TokenDTO tokenDTO;

    @Autowired
    EShopUserRepository eShopUserRepository;

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
    public void testEndpointForEShopUserCreationWithNoCredentials() throws Exception {
        EShopUserCreateDTO eShopUserCreateDTO = defaultEShopUserCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(EShopUserAPI.ROOT_PATH).content(
                                objectMapper.writeValueAsString(eShopUserCreateDTO)
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        EShopUserShowDTO eShopUserShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), EShopUserShowDTO.class);
        assertNotNull(eShopUserShowDTO.getEShopUserId());
        assertEquals(eShopUserCreateDTO.getFirstName(), eShopUserShowDTO.getFirstName());
        assertEquals(eShopUserCreateDTO.getLastName(), eShopUserShowDTO.getLastName());
        assertEquals(eShopUserCreateDTO.getEmail(), eShopUserShowDTO.getEmail());
        assertEquals(eShopUserCreateDTO.getPhoneNumber(), eShopUserShowDTO.getPhoneNumber());
        assertEquals(eShopUserCreateDTO.getAddress(), eShopUserShowDTO.getAddress());
        assertEquals(eShopUserCreateDTO.getBirthDate(), eShopUserShowDTO.getBirthDate());
        eShopUserRepository.deleteById(eShopUserShowDTO.getEShopUserId());
    }

    @Test
    public void testEndpointForEShopUserCreationWithNoCredentialsWhenThereIsAnotherUserWithTheEmail() throws Exception {
        EShopUserCreateDTO eShopUserCreateDTO = defaultEShopUserCreateDTO();
        eShopUserCreateDTO.setEmail("johndoe1@email.com");
        var result = mockMvc.perform(MockMvcRequestBuilders.post(EShopUserAPI.ROOT_PATH).content(
                                objectMapper.writeValueAsString(eShopUserCreateDTO)
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        ShopError shopError = objectMapper.readValue(result.getResponse().getContentAsString(), ShopError.class);
        List<ShopErrorDetail> icesiErrorDetailList = shopError.getDetails();
        assertEquals(HttpStatus.BAD_REQUEST, shopError.getStatus());
        assertEquals(icesiErrorDetailList.size(), 1);
        assertEquals(ErrorCode.ERR_400.getCode(), icesiErrorDetailList.get(0).getErrorCode());
        assertEquals("field email or phone number: There is already a user with the email " + eShopUserCreateDTO.getEmail() +". ", icesiErrorDetailList.get(0).getErrorMessage());
    }

    @Test
    public void testEndpointForEShopUserCreationWithNoCredentialsWhenThereIsAnotherUserWithThePhoneNumber() throws Exception {
        EShopUserCreateDTO eShopUserCreateDTO = defaultEShopUserCreateDTO();
        eShopUserCreateDTO.setPhoneNumber("+573101234567");
        var result = mockMvc.perform(MockMvcRequestBuilders.post(EShopUserAPI.ROOT_PATH).content(
                                objectMapper.writeValueAsString(eShopUserCreateDTO)
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        ShopError shopError = objectMapper.readValue(result.getResponse().getContentAsString(), ShopError.class);
        List<ShopErrorDetail> icesiErrorDetailList = shopError.getDetails();
        assertEquals(HttpStatus.BAD_REQUEST, shopError.getStatus());
        assertEquals(icesiErrorDetailList.size(), 1);
        assertEquals(ErrorCode.ERR_400.getCode(), icesiErrorDetailList.get(0).getErrorCode());
        assertEquals("field email or phone number: There is already a user with the phone number " + eShopUserCreateDTO.getPhoneNumber() +". ", icesiErrorDetailList.get(0).getErrorMessage());

    }

    @Test
    public void testEndpointForEShopUserRoleAssignationWithAdminCredentials() throws Exception {
        loginAsAdmin();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(EShopUserAPI.ROOT_PATH+"/df17e266-dcc4-4bf2-923c-bb5559722f50/role/"+ RoleType.ADMIN)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        EShopUserShowDTO eShopUserShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), EShopUserShowDTO.class);
        assertEquals("df17e266-dcc4-4bf2-923c-bb5559722f50", eShopUserShowDTO.getEShopUserId().toString());
        assertEquals("John", eShopUserShowDTO.getFirstName());
        assertEquals("Doe", eShopUserShowDTO.getLastName());
        assertEquals("johndoe2@email.com", eShopUserShowDTO.getEmail());
        assertEquals("+573111234567", eShopUserShowDTO.getPhoneNumber());
        assertNull(eShopUserShowDTO.getAddress());
        assertNull(eShopUserShowDTO.getBirthDate());
        loginAsUser();
        assertEquals(RoleType.ADMIN.toString(), tokenDTO.getRole());
        mockMvc.perform(MockMvcRequestBuilders.patch(EShopUserAPI.ROOT_PATH+"/df17e266-dcc4-4bf2-923c-bb5559722f50/role/"+ RoleType.USER));
    }

    @Test
    public void testEndpointForEShopUserRoleAssignationWithUserCredentials() throws Exception {
        loginAsUser();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(EShopUserAPI.ROOT_PATH+"/df17e266-dcc4-4bf2-923c-bb5559722f50/role/"+ RoleType.ADMIN)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testEndpointForEShopUserRoleAssignationWithShopCredentials() throws Exception {
        loginAsShop();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(EShopUserAPI.ROOT_PATH+"/df17e266-dcc4-4bf2-923c-bb5559722f50/role/"+ RoleType.ADMIN)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testEndpointForEShopUserRoleAssignationWhenTheEShopUserDoesNotExist() throws Exception {
        loginAsAdmin();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(EShopUserAPI.ROOT_PATH+"/1f17e266-dcc4-4bf2-923c-bb5559722f50/role/"+ RoleType.ADMIN)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        ShopError shopError = objectMapper.readValue(result.getResponse().getContentAsString(), ShopError.class);
        List<ShopErrorDetail> icesiErrorDetailList = shopError.getDetails();
        assertEquals(HttpStatus.NOT_FOUND, shopError.getStatus());
        assertEquals(icesiErrorDetailList.size(), 1);
        assertEquals(ErrorCode.ERR_404.getCode(), icesiErrorDetailList.get(0).getErrorCode());
        assertEquals("e-shop user with id: 1f17e266-dcc4-4bf2-923c-bb5559722f50 not found", icesiErrorDetailList.get(0).getErrorMessage());
    }

    @Test
    public void testEndpointForEShopUserRoleAssignationWhenTheRoleDoesNotExist() throws Exception {
        loginAsAdmin();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(EShopUserAPI.ROOT_PATH+"/df17e266-dcc4-4bf2-923c-bb5559722f50/role/ROLE")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        ShopError shopError = objectMapper.readValue(result.getResponse().getContentAsString(), ShopError.class);
        List<ShopErrorDetail> icesiErrorDetailList = shopError.getDetails();
        assertEquals(HttpStatus.NOT_FOUND, shopError.getStatus());
        assertEquals(icesiErrorDetailList.size(), 1);
        assertEquals(ErrorCode.ERR_404.getCode(), icesiErrorDetailList.get(0).getErrorCode());
        assertEquals("role with name: ROLE not found", icesiErrorDetailList.get(0).getErrorMessage());
    }
}
