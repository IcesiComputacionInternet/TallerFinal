package co.com.icesi.backend.integrationTests;

import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.LoginDTO;
import co.com.icesi.backend.dto.request.RequestUserDTO;
import co.com.icesi.backend.dto.request.TokenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles="test")

public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateAUser_HappyPath() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/users/create").content(
                                objectMapper.writeValueAsString(
                                        RequestUserDTO.builder()
                                                .email("sara@gmail.com")
                                                .phoneNumber("+573254789615")
                                                .firstName("Sara")
                                                .lastName("Alvarez")
                                                .address("Calle 5A # 9-15")
                                                .password("password")
                                                .birthday("2023-06-05")
                                                .role(UserRole.USER.getRole())
                                                .build()
                                ))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
    @Test
    public void testCreateAUserWhenRoleNameIsNull() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/users/create").content(
                                objectMapper.writeValueAsString(
                                        RequestUserDTO.builder()
                                                .email("sara@gmail.com")
                                                .phoneNumber("+573254789615")
                                                .firstName("Sara")
                                                .lastName("Alvarez")
                                                .address("Calle 5A # 9-15")
                                                .password("password")
                                                .birthday("2023-06-05")
                                                .role(null)
                                                .build()
                                ))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateAUserWhenRoleNameIsBlank() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/users/create").content(
                                objectMapper.writeValueAsString(
                                        RequestUserDTO.builder()
                                                .email("sara@gmail.com")
                                                .phoneNumber("+573254789615")
                                                .firstName("Sara")
                                                .lastName("Alvarez")
                                                .address("Calle 5A # 9-15")
                                                .password("password")
                                                .birthday("2023-06-05")
                                                .role("")
                                                .build()
                                ))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateAUserWhenRoleDoesNotExists() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/users/create").content(
                                objectMapper.writeValueAsString(
                                        RequestUserDTO.builder()
                                                .email("sara@gmail.com")
                                                .phoneNumber("+573254789615")
                                                .firstName("Sara")
                                                .lastName("Alvarez")
                                                .address("Calle 5A # 9-15")
                                                .password("password")
                                                .birthday("2023-06-05")
                                                .role("MANAGER")
                                                .build()
                                ))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateAdminUser() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/users/create").content(
                                objectMapper.writeValueAsString(
                                        RequestUserDTO.builder()
                                                .email("sara@gmail.com")
                                                .phoneNumber("+573254789615")
                                                .firstName("Sara")
                                                .lastName("Alvarez")
                                                .address("Calle 5A # 9-15")
                                                .password("password")
                                                .birthday("2023-06-05")
                                                .role(UserRole.ADMIN.getRole())
                                                .build()
                                ))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateShopUser() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/users/create").content(
                                objectMapper.writeValueAsString(
                                        RequestUserDTO.builder()
                                                .email("sara@gmail.com")
                                                .phoneNumber("+573254789615")
                                                .firstName("Sara")
                                                .lastName("Alvarez")
                                                .address("Calle 5A # 9-15")
                                                .password("password")
                                                .birthday("2023-06-05")
                                                .role(UserRole.SHOP.getRole())
                                                .build()
                                ))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateAUserWithAnEmailThatAlreadyExists() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/users/create").content(
                                objectMapper.writeValueAsString(
                                        RequestUserDTO.builder()
                                                .email("lauramartinez@gmail.com")
                                                .phoneNumber("+573254789615")
                                                .firstName("Sara")
                                                .lastName("Alvarez")
                                                .address("Calle 5A # 9-15")
                                                .password("password")
                                                .birthday("2023-06-05")
                                                .role(UserRole.USER.getRole())
                                                .build()
                                ))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateAUserWithAnPhoneThatAlreadyExists() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/users/create").content(
                                objectMapper.writeValueAsString(
                                        RequestUserDTO.builder()
                                                .email("lauramartinez@gmail.com")
                                                .phoneNumber("+573175933339")
                                                .firstName("Sara")
                                                .lastName("Alvarez")
                                                .address("Calle 5A # 9-15")
                                                .password("password")
                                                .birthday("2023-06-05")
                                                .role(UserRole.USER.getRole())
                                                .build()
                                ))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
