package co.icesi.automoviles.utils;

import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.EShopUserCreateDTO;

import java.time.LocalDateTime;

public class DTOBuilder {
    public static CategoryCreateDTO defaultCategoryCreateDTO(){
        return CategoryCreateDTO.builder()
                .name("Category 1")
                .description("Category for test 1")
                .build();
    }

    public static EShopUserCreateDTO defaultEShopUserCreateDTO(){
        return EShopUserCreateDTO.builder()
                .firstName("User 1")
                .lastName("User lastname")
                .email("user@gmail.com")
                .phoneNumber("+57 3101234567")
                .address("Calle 100")
                .birthDate(LocalDateTime.now())
                .password("password")
                .build();
    }
}
