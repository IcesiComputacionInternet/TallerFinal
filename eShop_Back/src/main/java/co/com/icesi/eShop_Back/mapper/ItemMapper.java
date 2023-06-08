package co.com.icesi.eShop_Back.mapper;

import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseItemDTO;
import co.com.icesi.eShop_Back.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "category", source = "category",ignore=true)
    ResponseItemDTO fromItem(Item item);
    @Mapping(target = "category", source = "category",ignore=true)
    Item fromItemDTO(RequestItemDTO responseItemDTO);
}
