package co.com.icesi.eShopBackEnd.mapper;

import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import co.com.icesi.eShopBackEnd.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category fromCreateCategoryDTO (CreateCategoryDTO createCategoryDTO);
    CreateCategoryDTO fromCategory(Category category);
}
