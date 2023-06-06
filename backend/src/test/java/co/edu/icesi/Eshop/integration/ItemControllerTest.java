package co.edu.icesi.Eshop.integration;

import co.edu.icesi.Eshop.TestConfigurationData;
import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.dto.LoginDTO;
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

import static co.edu.icesi.Eshop.api.ItemAPI.BASE_ITEM_URL;
import static org.junit.jupiter.api.Assertions.*;
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
    public class testCreateItemHappyPath {

        @Test
        public void testCreateItemFromAdmin() throws Exception {
            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(defaultItem())
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            ItemDTO itemDTO = objectMapper.readValue(newResult.getResponse().getContentAsString(), ItemDTO.class);
            assertNotNull(itemDTO);
            assertEquals(itemDTO.getName(), "Aspiradora 300");
        }

        @Test
        public void testCreateItemFromShop() throws Exception {
            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(defaultItem2())
                            )
                            .header("Authorization", "Bearer " + generateShopToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            ItemDTO itemDTO = objectMapper.readValue(newResult.getResponse().getContentAsString(), ItemDTO.class);
            assertNotNull(itemDTO);
            assertEquals(itemDTO.getName(), "Hidrolavadora 20L");
        }
    }

    @Nested
    public class testsCreateItemNotHappyPath {

        @Test
        public void testCreateItemFromUser() throws Exception {
            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(defaultItem())
                            )
                            .header("Authorization", "Bearer " + generateUserToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized())
                    .andReturn();


            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("You are not authorized", detail.getErrorMessage());
            assertEquals(401, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithNameAlreadyExists() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setName("Nevera 250L");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isConflict())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("Item with name " + itemDTO.getName() + " already exists", detail.getErrorMessage());
            assertEquals(409, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithInvalidPrice() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setPrice(-100000L);

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("price min value is 1", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithInvalidGuarantee() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setGuarantee(-1);

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("guarantee min value is 0", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithInvalidLevelOfEfficiency() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setLevelOfEfficiency("L");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("levelOfEfficiency Invalid level of efficiency", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithMissingCategory() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setCategory("");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("category is missing", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithMissingSourceEnergy() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setSourceOfEnergy("");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("sourceOfEnergy is missing", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithMissingDescription() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setDescription("");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("description is missing", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithMissingMarca() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setMarca("");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("marca is missing", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithMissingModel() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setModel("");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("model is missing", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithMissingImage() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setImageUrl("");

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("imageUrl is missing", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithInvalidMinVoltage() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setMinVoltage(-1L);

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("minVoltage min value is 0", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }

        @Test
        public void testCreateItemWithInvalidMaxVoltage() throws Exception {

            ItemDTO itemDTO = defaultItem();
            itemDTO.setMaxVoltage(-1L);

            var newResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_ITEM_URL).content(
                                    objectMapper.writeValueAsString(itemDTO)
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("maxVoltage min value is 0", detail.getErrorMessage());
            assertEquals(400, newResult.getResponse().getStatus());
        }
    }

    @Nested
    public class testsSetItemsHappyPath{

        @Test
        public void testSetItemStateFromAdmin() throws Exception {

            String itemName = "Plancha 30V";

            var newResult = mockMvc.perform(MockMvcRequestBuilders.put(BASE_ITEM_URL+"/setState/"+itemName).content(
                                    objectMapper.writeValueAsString(defaultItem())
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            ItemDTO newItemDTO = objectMapper.readValue(newResult.getResponse().getContentAsString(), ItemDTO.class);
            assertNotNull(newItemDTO);
            assertFalse(newItemDTO.isAvailable());
        }
    }

    @Nested
    public class testsSetItemsNotHappyPath{

        @Test
        public void testSetNotExistsItem() throws Exception {

            String itemName = "Licuadora";

            var newResult = mockMvc.perform(MockMvcRequestBuilders.put(BASE_ITEM_URL+"/setState/"+itemName).content(
                                    objectMapper.writeValueAsString(defaultItem())
                            )
                            .header("Authorization", "Bearer " + generateAdminToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("Item with name Licuadora not found", detail.getErrorMessage());
            assertEquals(404, newResult.getResponse().getStatus());
        }

        @Test
        public void testSetItemFromShop() throws Exception {

            String itemName = "Licuadora";

            var newResult = mockMvc.perform(MockMvcRequestBuilders.put(BASE_ITEM_URL+"/setState/"+itemName).content(
                                    objectMapper.writeValueAsString(defaultItem())
                            )
                            .header("Authorization", "Bearer " + generateShopToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("You are not authorized", detail.getErrorMessage());
            assertEquals(401, newResult.getResponse().getStatus());
        }

        @Test
        public void testSetItemFromUser() throws Exception {

            String itemName = "Licuadora";

            var newResult = mockMvc.perform(MockMvcRequestBuilders.put(BASE_ITEM_URL+"/setState/"+itemName).content(
                                    objectMapper.writeValueAsString(defaultItem())
                            )
                            .header("Authorization", "Bearer " + generateUserToken().getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized())
                    .andReturn();

            EShopError eShopError = objectMapper.readValue(newResult.getResponse().getContentAsString(), EShopError.class);
            assertNotNull(eShopError);
            var details = eShopError.getDetails();
            assertEquals(1, details.size());
            var detail = details.get(0);

            assertEquals("You are not authorized", detail.getErrorMessage());
            assertEquals(401, newResult.getResponse().getStatus());
        }

    }

    private ItemDTO defaultItem(){
        return ItemDTO.builder()
                .name("Aspiradora 300")
                .description("Aspiradora Xiaomi para la casa")
                .category("Cuidado del hogar")
                .imageUrl("https://www.alkosto.com/medias/6934177747205-001-750Wx750H?context=bWFzdGVyfGltYWdlc3w2NzgzN3xpbWFnZS9qcGVnfGltYWdlcy9oNTUvaDYwLzExNzQxODYyODg3NDU0LmpwZ3xkOTRiMDdlODllYTI0Y2ZjMDYwN2JmYzAzNDFiZTA3M2E0YzRhZTU5NTFjZjkyMDFmNjJkMmZhNjZhNGQwMjI5")
                .price(26000000L)
                .minVoltage(1.5)
                .maxVoltage(5.0)
                .sourceOfEnergy("Energía por bateria")
                .levelOfEfficiency("C")
                .marca("Xiaomi")
                .model("2024")
                .guarantee(24)
                .build();
    }

    private ItemDTO defaultItem2(){
        return ItemDTO.builder()
                .name("Hidrolavadora 20L")
                .description("Hidrolavadora Kalley")
                .category("Cuidado del hogar")
                .imageUrl("https://www.alkosto.com/medias/7705946475709-001-750Wx750H?context=bWFzdGVyfGltYWdlc3w5MDU3NXxpbWFnZS9qcGVnfGltYWdlcy9oYjEvaGEyLzEwNjc2NTA3NzM4MTQyLmpwZ3wxMzNjODgwZGE4ZGQwNjdhYzY1ZmUxZTcwNWM1YWRlOWQxNTI5YmNhNjUxMDM3YmQyNTVhNmM3ZTk0NmVmYWMx")
                .price(180000L)
                .minVoltage(1.1)
                .maxVoltage(3.3)
                .sourceOfEnergy("Energía por cable")
                .levelOfEfficiency("B")
                .marca("Kalley")
                .model("2022")
                .guarantee(12)
                .build();
    }
}
