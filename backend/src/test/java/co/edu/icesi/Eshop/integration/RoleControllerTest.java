package co.edu.icesi.Eshop.integration;

import co.edu.icesi.Eshop.TestConfigurationData;
import co.edu.icesi.Eshop.dto.LoginDTO;
import co.edu.icesi.Eshop.dto.RoleDTO;
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

import static co.edu.icesi.Eshop.api.RoleAPI.BASE_ROLE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    public class testsCreateRoleHappyPath{

        @Test
        public void testCreateRoleFromAdmin() throws Exception{
            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROLE_URL).content(
                                    objectMapper.writeValueAsString(defaultRole())
                            )
                            .header("Authorization","Bearer "+generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            RoleDTO role = objectMapper.readValue(newResult.getResponse().getContentAsString(), RoleDTO.class);
            assertNotNull(role);
            assertEquals(role.getRoleName(),"Example");
        }
    }

    @Nested
    public class testsCreateRoleNotHappyPath{

        @Test
        public void testCreateRoleWithNameThatAlreadyExists() throws Exception{
            RoleDTO role = defaultRole();
            role.setRoleName("ADMIN");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROLE_URL).content(
                                    objectMapper.writeValueAsString(role)
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

            assertEquals("Role with name "+role.getRoleName()+" already exists",detail.getErrorMessage());
            assertEquals(409, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateRoleFromShop() throws Exception{
            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROLE_URL).content(
                                    objectMapper.writeValueAsString(defaultRole())
                            )
                            .header("Authorization","Bearer "+generateShopToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden())
                    .andReturn();

            assertEquals(403, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateRoleFromUser() throws Exception{
            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROLE_URL).content(
                                    objectMapper.writeValueAsString(defaultRole())
                            )
                            .header("Authorization","Bearer "+generateUserToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden())
                    .andReturn();

            assertEquals(403, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateRoleWithMissingName() throws Exception{

            RoleDTO roleDTO = defaultRole();
            roleDTO.setRoleName("");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ROLE_URL).content(
                                    objectMapper.writeValueAsString(roleDTO)
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

            assertEquals("roleName A name is required for the role", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }
    }

    private RoleDTO defaultRole(){
        return RoleDTO.builder()
                .roleName("Example")
                .description("Example role")
                .build();
    }


}
