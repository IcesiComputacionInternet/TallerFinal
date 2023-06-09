package co.icesi.automoviles.integration;

import co.icesi.automoviles.TestConfigurationData;
import co.icesi.automoviles.api.ItemAPI;
import co.icesi.automoviles.api.PurchaseOrderAPI;
import co.icesi.automoviles.dto.*;
import co.icesi.automoviles.enums.PurchaseOrderStatus;
import co.icesi.automoviles.repository.PurchaseOrderRepository;
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

import static co.icesi.automoviles.utils.DTOBuilder.defaultPurchaseOrderCreateDTOForAdmin;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
public class PurchaseOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private TokenDTO tokenDTO;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

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

    private void deletePurchaseOrder(String uuid) throws Exception {
        purchaseOrderRepository.deleteById(UUID.fromString(uuid));
    }

    @Test
    public void testEndpointForPurchaseOrderCreationWithAdminCredentialsForHimself() throws Exception {
        loginAsAdmin();
        PurchaseOrderCreateDTO purchaseOrderCreateDTO = defaultPurchaseOrderCreateDTOForAdmin();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(PurchaseOrderAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(purchaseOrderCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        PurchaseOrderShowDTO purchaseOrderShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), PurchaseOrderShowDTO.class);
        assertNotNull(purchaseOrderShowDTO.getPurchaseOrderId());
        assertEquals(purchaseOrderCreateDTO.getEShopUserUUID(), purchaseOrderShowDTO.getEShopUser().getEShopUserId().toString());
        assertEquals(PurchaseOrderStatus.Processing_order.toString(), purchaseOrderShowDTO.getStatus());
        assertEquals(30000000, purchaseOrderShowDTO.getTotal());
        assertEquals(3, purchaseOrderShowDTO.getItems().size());
        assertEquals("e9c14553-3e76-4968-b78c-0d6fc8dfcdbb", purchaseOrderShowDTO.getItems().get(0).getItemId().toString());
        assertEquals("30eadfff-5cc6-4968-9755-11de28678e38", purchaseOrderShowDTO.getItems().get(1).getItemId().toString());
        assertEquals("5f81aa60-fb85-4de6-b7c4-824ea6b7fdf1", purchaseOrderShowDTO.getItems().get(2).getItemId().toString());

    }

    @Test
    public void testEndpointForPurchaseOrderCreationWithAdminCredentialsForOther(){

    }

    @Test
    public void testEndpointForPurchaseOrderCreationWithUserCredentialsForHimself(){

    }

    @Test
    public void testEndpointForPurchaseOrderCreationWithUserCredentialsForOther(){

    }

    @Test
    public void testEndpointForPurchaseOrderCreationWithShop(){

    }

    @Test
    public void testEndpointForGetPurchaseOrderByIdWithAdminCredentialsWhenAdminIsTheOwner(){

    }

    @Test
    public void testEndpointForGetPurchaseOrderByIdWithAdminCredentialsWhenAdminIsNotTheOwner(){

    }

    @Test
    public void testEndpointForGetPurchaseOrderByIdWithUserCredentialsWhenUserIsTheOwner(){

    }

    @Test
    public void testEndpointForGetPurchaseOrderByIdWithUserCredentialsWhenUserIsNotTheOwner(){

    }

    @Test
    public void testEndpointForGetPurchaseOrderByIdWithShopCredentialsWhenShopIsTheOwner(){

    }

    @Test
    public void testEndpointForGetPurchaseOrderByIdWithShopCredentialsWhenShopIsNotTheOwner(){

    }

    @Test
    public void testEndpointForGetAllPurchaseOrderWithAdminCredentials(){

    }

    @Test
    public void testEndpointForGetAllPurchaseOrderWithUserCredentials(){

    }

    @Test
    public void testEndpointForGetAllPurchaseOrderWithShopCredentials(){

    }

    @Test
    public void testEndpointForDeletePurchaseOrderWithAdminCredentials(){

    }

    @Test
    public void testEndpointForDeletePurchaseOrderWithUserCredentials(){

    }

    @Test
    public void testEndpointForDeletePurchaseOrderWithShopCredentials(){

    }

    @Test
    public void testEndpointForUpdatePurchaseOrderStateWithAdminCredentials(){

    }

    @Test
    public void testEndpointForUpdatePurchaseOrderStateWithUserCredentials(){

    }

    @Test
    public void testEndpointForUpdatePurchaseOrderStateWithShopCredentials(){

    }

    @Test
    public void testEndpointForUpdatePurchaseOrderWithAdminCredentials(){

    }

    @Test
    public void testEndpointForUpdatePurchaseOrderWithUserCredentials(){

    }

    @Test
    public void testEndpointForUpdatePurchaseOrderWithShopCredentials(){

    }
}
