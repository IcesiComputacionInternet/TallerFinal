package co.icesi.automoviles.utils;

import co.icesi.automoviles.dto.CategoryCreateDTO;

public class DTOBuilder {
    public static CategoryCreateDTO defaultCategoryCreateDTO(){
        return CategoryCreateDTO.builder()
                .name("Category 1")
                .description("Category for test 1")
                .build();
    }
}
