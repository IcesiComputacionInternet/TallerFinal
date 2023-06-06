package co.edu.icesi.Eshop.integration;

import co.edu.icesi.Eshop.TestConfigurationData;
import co.edu.icesi.Eshop.dto.*;
import co.edu.icesi.Eshop.model.EShopOrder;
import co.edu.icesi.Eshop.model.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static co.edu.icesi.Eshop.api.OrderAPI.BASE_ORDER_URL;
import static co.edu.icesi.Eshop.api.UserAPI.BASE_USER_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles(profiles="test")
@Import(TestConfigurationData.class)
class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    public TokenDTO tokenEndpoint(String email, String password) throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO(email, password))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(), TokenDTO.class);
    }

    private TokenDTO tokenAdmin() throws Exception {
        return tokenEndpoint("johndoe@email.com", "password");
    }

    private TokenDTO tokenUser() throws Exception {
        return tokenEndpoint("johndoe3@email.com", "password");
    }

    private TokenDTO tokenShop() throws Exception {
        return tokenEndpoint("johndoe2@email.com", "password");
    }

    @Test
    public void testCreateOrder() throws Exception {

        TokenDTO tokenDTO = tokenUser();

        var result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ORDER_URL).content(
                                objectMapper.writeValueAsString(defaultOrderDTO())
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        OrderDTO orderDTO = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);
        assertNotNull(orderDTO);
        assertEquals("johndoe3@email.com",orderDTO.getUserEmail());
        assertEquals(200000,orderDTO.getTotal());

    }

    @Test
    public void testCreateOrder2() throws Exception {

        TokenDTO tokenDTO = tokenAdmin();

        var result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ORDER_URL).content(
                                objectMapper.writeValueAsString(defaultOrderDTO2())
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        OrderDTO orderDTO = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);
        assertNotNull(orderDTO);
        assertEquals("johndoe@email.com",orderDTO.getUserEmail());
        assertEquals(1900000L,orderDTO.getTotal());

    }

    @Test
    public void testChangeStatusOrder() throws Exception {

        TokenDTO tokenDTO = tokenShop();

        var result = mockMvc.perform(MockMvcRequestBuilders.put(BASE_ORDER_URL+"/d1e854e9-129e-4958-80b5-23599d72d42b").content(
                                objectMapper.writeValueAsString(defaultChangeStatusDTO())
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenDTO.getToken()))
                .andExpect(status().isOk())
                .andReturn();
        OrderDTO orderDTO = objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);
        assertNotNull(orderDTO);
        assertEquals("johndoe3@email.com",orderDTO.getUserEmail());
        assertEquals(1700000,orderDTO.getTotal());
        assertEquals(Status.SHIPPED.toString(),orderDTO.getStatus());

    }

    private OrderDTO defaultOrderDTO(){
        return OrderDTO.builder()
                .items(Stream.of("Plancha 30V").collect(Collectors.toList()))
                .build();
    }

    private OrderDTO defaultOrderDTO2(){
        return OrderDTO.builder()
                .items(Stream.of("Nevera 250L","Plancha 30V").collect(Collectors.toList()))
                .build();
    }

    private ChangeStatusDTO defaultChangeStatusDTO(){
        return ChangeStatusDTO.builder()
                .orderId("d1e854e9-129e-4958-80b5-23599d72d42b")
                .status(Status.SHIPPED.toString())
                .build();
    }
}
