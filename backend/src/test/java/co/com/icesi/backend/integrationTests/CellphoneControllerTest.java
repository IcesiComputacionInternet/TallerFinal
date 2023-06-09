package co.com.icesi.backend.integrationTests;

import co.com.icesi.backend.Enum.CategoryType;
import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.LoginDTO;
import co.com.icesi.backend.dto.request.RequestCellphoneDTO;
import co.com.icesi.backend.dto.request.RoleDTO;
import co.com.icesi.backend.dto.request.TokenDTO;
import co.com.icesi.backend.model.Cellphone;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Import(TestConfigurationData.class)
@ActiveProfiles(profiles="test")

public class CellphoneControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testAdminCreatesACellphoneHappy_Path() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung A42 128 GB Azul")
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testShopUserCreatesACellphoneHappy_Path() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("kerenlopez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung A51 128 GB Blanco")
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateACellphoneWhenAnormalUserIsLoggedIn() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("luismiguel@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung A51 128 GB Blanco")
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
    @Test
    public void testCreateACellphoneWithACategoryThatDoesNotExists() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung A51 128 GB Blanco")
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category("MID-HIGH-RANGE")
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
    @Test
    public void testCreateACellphoneWithANameThatAlreadyExists() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung Galaxy A22 5G 128 GB Gris")
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateACellphoneWithANullName() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name(null)
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateACellphoneWithANullDescription() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung A51 128 GB Blanco")
                                                .description(null)
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateACellphoneWithABlankName() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("")
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateARoleWithABlankDescription() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung A51 128 GB Blanco")
                                                .description("")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateACellphoneWithAZeroPrice() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung A51 128 GB Blanco")
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(0L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(10)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateACellphoneWithAZeroStock() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/cellphones/create").content(
                                objectMapper.writeValueAsString(
                                        RequestCellphoneDTO.builder()
                                                .name("Celular Samsung A51 128 GB Blanco")
                                                .description("Fotografía profesional en tu bolsillo Descubre infinitas posibilidades " +
                                                        "para tus fotos con las 3 cámaras principales de tu equipo. " +
                                                        "Pon a prueba tu creatividad y juega con la iluminación," +
                                                        " diferentes planos y efectos para obtener grandes resultados.")
                                                .price(1200000L)
                                                .imageUrl("https://www.cqnetcr.com/80315-thickbox_default/celular-samsung-galaxy-a51-128gb-5mp-48mp-12mp-mpg.jpg")
                                                .brand("Samsung Galaxy")
                                                .storage("128Gb")
                                                .ram("4")
                                                .operatingSystem("Android 10")
                                                .frontCameraResolution("Single 32 MP")
                                                .mainCameraResolution("48 Mpx")
                                                .screenSize("5″")
                                                .stock(0)
                                                .category(CategoryType.MID_RANGE.getCategory())
                                                .build()
                                ))
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    /*@Test
    public void testGetACellphone() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/cellphones/{name}", UserRole.SHOP.getRole())
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testGetAllCategories() throws Exception {
        var resultToken = mockMvc.perform(MockMvcRequestBuilders.post("/token").content(
                                objectMapper.writeValueAsString(new LoginDTO("lauramartinez@gmail.com", "password"))
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        TokenDTO token = objectMapper.readValue(resultToken.getResponse().getContentAsString(),TokenDTO.class);
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/roles/getRoles")
                        .header("Authorization", "Bearer "+token.getToken())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }*/
}
