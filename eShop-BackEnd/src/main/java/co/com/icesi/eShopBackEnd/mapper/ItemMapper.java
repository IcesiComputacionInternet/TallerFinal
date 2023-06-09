package co.com.icesi.eShopBackEnd.mapper;

import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import co.com.icesi.eShopBackEnd.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "category", source = "category",ignore = true)
    Item fromCreateItemDTO (CreateItemDTO itemDTO);

    @Mapping(target = "category", source = "category",ignore = true)
    CreateItemDTO fromItem(Item item);

    ResponseItemDTO fromItemToResponseItemDTO(Item item);

    Item fromResponseItemDTOToItem(ResponseItemDTO responseItemDTO);


}
