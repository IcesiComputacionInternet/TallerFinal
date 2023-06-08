package co.com.icesi.eShop_Back.mapper;

import co.com.icesi.eShop_Back.dto.request.RequestCategoryDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseCategoryDTO;
import co.com.icesi.eShop_Back.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromCategoryDTO(RequestCategoryDTO categoryDTO);
    @Mapping(target = "items", source = "items",ignore=true)
    ResponseCategoryDTO fromCategory(Category category);

}
