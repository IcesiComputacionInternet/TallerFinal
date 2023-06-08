package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.CategoryShowDTOForItem;
import org.mapstruct.Mapper;

import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.CategoryShowDTO;
import co.icesi.automoviles.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category fromCategoryCreateDTOToCategory(CategoryCreateDTO categoryCreateDTO);
    CategoryShowDTO fromCategoryToCategoryShowDTO(Category category);
    CategoryShowDTOForItem fromCategoryToCategoryShowDTOFromItem(Category category);
}
