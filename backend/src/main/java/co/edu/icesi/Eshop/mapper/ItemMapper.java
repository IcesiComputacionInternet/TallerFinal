package co.edu.icesi.Eshop.mapper;

import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "category", source = "category",ignore=true)
    Item fromItemDTO(ItemDTO itemDTO);

    @Mapping(target = "category", expression = "java(category.getName())")
    ItemDTO fromItem(Item item);
}
