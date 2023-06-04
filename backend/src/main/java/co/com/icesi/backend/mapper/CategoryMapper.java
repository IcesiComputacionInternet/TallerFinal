package co.com.icesi.backend.mapper;

import co.com.icesi.backend.dto.request.CategoryDTO;
import co.com.icesi.backend.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category fromCategoryDTO(CategoryDTO categoryDTO);
    CategoryDTO fromCategoryToCategoryDTO(Category category);
}
