package co.com.icesi.backend.integrationTests;

import co.com.icesi.backend.Enum.OrderStatus;
import co.com.icesi.backend.dto.request.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles(profiles="test")
@Import(TestConfigurationData.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateOrder_HappyPath() throws Exception{
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("luismiguel@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(), TokenDTO.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders/create").content(
                                objectMapper.writeValueAsString(
                                        defaultOrderDTO()
                                ))
                        .header("Authorization", "Bearer " + token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testChangeOrderStatus_HappyPath() throws Exception{
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("kerenlopez@gmail.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(), TokenDTO.class);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/orders/changeStatus").content(
                                objectMapper.writeValueAsString(
                                        defaultChangeOrderStatus()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(token);
    }

    @Test
    public void testGetOrder_HappyPath() throws Exception{
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("kerenlopez@gmail.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(), TokenDTO.class);

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/{id}", "de7ae704-6bbf-47da-925a-b0bdd13351cf")
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(token);
    }

    @Test
    public void testGetOrders_HappyPath() throws Exception{
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("kerenlopez@gmail.com", "password")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(), TokenDTO.class);

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/getAccounts")
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(token);
    }

    private RequestNewOrderDTO defaultOrderDTO() {
        List<RequestOrderItemDTO> cellphones = new ArrayList<>();
        cellphones.add(defaultOrderItemDTO());
        return RequestNewOrderDTO.builder()
                .userEmail("luismiguel@gmail.com")
                .total(3789000L)
                .items(cellphones)
                .build();
    }

    private RequestOrderItemDTO defaultOrderItemDTO(){
        return RequestOrderItemDTO.builder()
                .id(UUID.fromString("de7ae704-6bbf-47da-925a-b0bdd13351cf"))
                .quantity(5)
                .build();
    }

    private RequestChangeOrderDTO defaultChangeOrderStatus(){
        return RequestChangeOrderDTO.builder()
                .orderId(UUID.fromString("de7ae704-6bbf-47da-925a-b0bdd13351cf"))
                .newStatus(OrderStatus.SENT.getStatus())
                .build();
    }
}
