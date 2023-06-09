package co.icesi.automoviles.integration;

import co.icesi.automoviles.TestConfigurationData;
import co.icesi.automoviles.api.CategoryAPI;
import co.icesi.automoviles.api.EShopUserAPI;
import co.icesi.automoviles.api.ItemAPI;
import co.icesi.automoviles.dto.*;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.repository.ItemRepository;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;

import static co.icesi.automoviles.utils.DTOBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    TokenDTO tokenDTO;

    @Autowired
    ItemRepository itemRepository;

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
    public void  testEndpointForItemCreationWithAdminCredentials() throws Exception {
        loginAsAdmin();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(ItemAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ItemShowDTO itemShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ItemShowDTO.class);
        assertNotNull(itemShowDTO.getItemId());
        assertEquals(itemCreateDTO.getDescription(), itemShowDTO.getDescription());
        assertEquals(itemCreateDTO.getName(), itemShowDTO.getName());
        assertEquals(itemCreateDTO.getPrice(), itemShowDTO.getPrice());
        assertEquals(itemCreateDTO.getImageUrl(), itemShowDTO.getImageUrl());
        assertEquals(itemCreateDTO.getCategoryUUID(), itemShowDTO.getCategory().getCategoryId().toString());
        Optional<Item> item = itemRepository.findById(itemShowDTO.getItemId());
        if(item.isEmpty()){
            fail();
        }
        itemRepository.delete(item.get());
    }

    @Test
    public void  testEndpointForItemCreationWithUserCredentials() throws Exception {
        loginAsUser();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(ItemAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void  testEndpointForItemCreationWithShopCredentials() throws Exception {
        loginAsShop();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(ItemAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ItemShowDTO itemShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ItemShowDTO.class);
        assertNotNull(itemShowDTO.getItemId());
        assertEquals(itemCreateDTO.getDescription(), itemShowDTO.getDescription());
        assertEquals(itemCreateDTO.getName(), itemShowDTO.getName());
        assertEquals(itemCreateDTO.getPrice(), itemShowDTO.getPrice());
        assertEquals(itemCreateDTO.getImageUrl(), itemShowDTO.getImageUrl());
        assertEquals(itemCreateDTO.getCategoryUUID(), itemShowDTO.getCategory().getCategoryId().toString());
        Optional<Item> item = itemRepository.findById(itemShowDTO.getItemId());
        if(item.isEmpty()){
            fail();
        }
        itemRepository.delete(item.get());
    }

    @Test
    public void  testEndpointForItemEditionWithAdminCredentials() throws Exception {
        loginAsAdmin();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(ItemAPI.ROOT_PATH+"/e9c14553-3e76-4968-b78c-0d6fc8dfcdbb")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ItemShowDTO itemShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ItemShowDTO.class);
        assertEquals("e9c14553-3e76-4968-b78c-0d6fc8dfcdbb", itemShowDTO.getItemId().toString());
        assertEquals(itemCreateDTO.getDescription(), itemShowDTO.getDescription());
        assertEquals(itemCreateDTO.getName(), itemShowDTO.getName());
        assertEquals(itemCreateDTO.getPrice(), itemShowDTO.getPrice());
        assertEquals(itemCreateDTO.getImageUrl(), itemShowDTO.getImageUrl());
        assertEquals(itemCreateDTO.getCategoryUUID(), itemShowDTO.getCategory().getCategoryId().toString());
        ItemCreateDTO originalItem = ItemCreateDTO.builder()
                .description("Description for car 1")
                .name("Car 1")
                .price(10000000)
                .imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
                .categoryUUID("246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.patch(ItemAPI.ROOT_PATH+"/e9c14553-3e76-4968-b78c-0d6fc8dfcdbb")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                .content(objectMapper.writeValueAsString(originalItem))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void  testEndpointForItemEditionWithUserCredentials() throws Exception {
        loginAsUser();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(ItemAPI.ROOT_PATH+"/e9c14553-3e76-4968-b78c-0d6fc8dfcdbb")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void  testEndpointForItemEditionWithShopCredentials() throws Exception {
        loginAsShop();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.patch(ItemAPI.ROOT_PATH+"/e9c14553-3e76-4968-b78c-0d6fc8dfcdbb")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ItemShowDTO itemShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ItemShowDTO.class);
        assertEquals("e9c14553-3e76-4968-b78c-0d6fc8dfcdbb", itemShowDTO.getItemId().toString());
        assertEquals(itemCreateDTO.getDescription(), itemShowDTO.getDescription());
        assertEquals(itemCreateDTO.getName(), itemShowDTO.getName());
        assertEquals(itemCreateDTO.getPrice(), itemShowDTO.getPrice());
        assertEquals(itemCreateDTO.getImageUrl(), itemShowDTO.getImageUrl());
        assertEquals(itemCreateDTO.getCategoryUUID(), itemShowDTO.getCategory().getCategoryId().toString());
        ItemCreateDTO originalItem = ItemCreateDTO.builder()
                .description("Description for car 1")
                .name("Car 1")
                .price(10000000)
                .imageUrl("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500")
                .categoryUUID("246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.patch(ItemAPI.ROOT_PATH+"/e9c14553-3e76-4968-b78c-0d6fc8dfcdbb")
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                .content(objectMapper.writeValueAsString(originalItem))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void  testEndpointForGetItemByIdWithoutAnyCredentials() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.get(ItemAPI.ROOT_PATH+"/e9c14553-3e76-4968-b78c-0d6fc8dfcdbb")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ItemShowDTO itemShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ItemShowDTO.class);
        assertEquals("e9c14553-3e76-4968-b78c-0d6fc8dfcdbb", itemShowDTO.getItemId().toString());
        assertEquals("Description for car 1", itemShowDTO.getDescription());
        assertEquals("Car 1", itemShowDTO.getName());
        assertEquals(10000000, itemShowDTO.getPrice());
        assertEquals("https://img.freepik.com/fotos-premium/fondos-coches-deportivos-azules_2227-2.jpg?w=500", itemShowDTO.getImageUrl());
        assertEquals("246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae", itemShowDTO.getCategory().getCategoryId().toString());
        }

    @Test
    public void  testEndpointForGetAllItemsWithoutAnyCredentials() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.get(ItemAPI.ROOT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        var itemShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), LinkedHashMap.class);
        assertEquals(5, ((ArrayList) itemShowDTO.get("items")).size());
        assertEquals(1, itemShowDTO.get("page"));
        assertEquals(2, itemShowDTO.get("totalPages"));
        assertEquals(8, itemShowDTO.get("count"));
    }

    private ItemShowDTO createItem() throws Exception {
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.post(ItemAPI.ROOT_PATH)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        ItemShowDTO itemShowDTO = objectMapper.readValue(result.getResponse().getContentAsString(), ItemShowDTO.class);
        return itemShowDTO;
    }

    @Test
    public void  testEndpointForItemDeletionWithAdminCredentials() throws Exception {
        loginAsAdmin();
        ItemShowDTO itemShowDTO = createItem();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.delete(ItemAPI.ROOT_PATH+"/"+itemShowDTO.getItemId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void  testEndpointForItemDeletionWithUserCredentials() throws Exception {
        loginAsUser();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.delete(ItemAPI.ROOT_PATH+"/e9c14553-3e76-4968-b78c-0d6fc8dfcdbb")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void  testEndpointForItemDeletionWithShopCredentials() throws Exception {
        loginAsShop();
        ItemShowDTO itemShowDTO = createItem();
        ItemCreateDTO itemCreateDTO = defaultItemCreateDTO();
        var result = mockMvc.perform(MockMvcRequestBuilders.delete(ItemAPI.ROOT_PATH+"/"+itemShowDTO.getItemId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+tokenDTO.getToken())
                        .content(objectMapper.writeValueAsString(itemCreateDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }
}
