package co.icesi.automoviles.utils;

import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.EShopUserCreateDTO;
import co.icesi.automoviles.dto.ItemCreateDTO;
import co.icesi.automoviles.dto.RoleCreateDTO;

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

    public static RoleCreateDTO defaultRoleCreateDTO(){
        return RoleCreateDTO.builder()
                .roleName("Role 1")
                .description("Description for Role 1")
                .build();
    }

    public static ItemCreateDTO defaultItemCreateDTO(){
        return ItemCreateDTO.builder()
                .description("Item for testing")
                .name("Item test")
                .price(1000)
                .imageUrl("imageURL")
                .categoryUUID("246ccb5e-33e8-4d5a-9dc1-06bdc0ecf3ae")
                .build();
    }
}
