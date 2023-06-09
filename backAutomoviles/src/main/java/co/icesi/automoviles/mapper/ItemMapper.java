package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.ItemCreateDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item fromItemCreateDTOToItem(ItemCreateDTO itemCreateDTO);
    @Mapping(target = "category", ignore = true)
    ItemShowDTO fromItemToItemShowDTO(Item item);
}
