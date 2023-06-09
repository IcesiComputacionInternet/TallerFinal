package co.icesi.automoviles.utils;

import co.icesi.automoviles.dto.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public static PurchaseOrderCreateDTO defaultPurchaseOrderCreateDTOForAdmin(){
        ArrayList<String> items = new ArrayList<>();
        items.add("e9c14553-3e76-4968-b78c-0d6fc8dfcdbb");
        items.add("30eadfff-5cc6-4968-9755-11de28678e38");
        items.add("5f81aa60-fb85-4de6-b7c4-824ea6b7fdf1");
        return PurchaseOrderCreateDTO.builder()
                .eShopUserUUID("04dacbf5-4815-4d6e-a2a7-db01a607f237")
                .items(items)
                .build();
    }

    public static PurchaseOrderCreateDTO defaultPurchaseOrderCreateDTOForUser(){
        ArrayList<String> items = new ArrayList<>();
        items.add("e9c14553-3e76-4968-b78c-0d6fc8dfcdbb");
        items.add("30eadfff-5cc6-4968-9755-11de28678e38");
        items.add("5f81aa60-fb85-4de6-b7c4-824ea6b7fdf1");
        return PurchaseOrderCreateDTO.builder()
                .eShopUserUUID("df17e266-dcc4-4bf2-923c-bb5559722f50")
                .items(items)
                .build();
    }

    public static PurchaseOrderCreateDTO defaultPurchaseOrderCreateDTOForShop(){
        ArrayList<String> items = new ArrayList<>();
        items.add("e9c14553-3e76-4968-b78c-0d6fc8dfcdbb");
        items.add("30eadfff-5cc6-4968-9755-11de28678e38");
        items.add("5f81aa60-fb85-4de6-b7c4-824ea6b7fdf1");
        return PurchaseOrderCreateDTO.builder()
                .eShopUserUUID("af17e266-dcc4-4bf2-923c-bb5559722f50")
                .items(items)
                .build();
    }
}
