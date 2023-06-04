package co.edu.icesi.Eshop.mapper;

import co.edu.icesi.Eshop.dto.CategoryDTO;
import co.edu.icesi.Eshop.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromCategoryDTO(CategoryDTO categoryDTO);

    CategoryDTO fromCategory(Category category);
}
