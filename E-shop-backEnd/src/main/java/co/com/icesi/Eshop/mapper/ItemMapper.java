package co.com.icesi.Eshop.mapper;

import co.com.icesi.Eshop.dto.request.ItemDTO;
import co.com.icesi.Eshop.dto.response.ItemResponseDTO;
import co.com.icesi.Eshop.model.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItem(ItemDTO itemDTO);

    Item toItem(ItemResponseDTO itemResponseDTO);

    ItemDTO toItemDTO(Item item);

    ItemDTO toItemDTO(ItemResponseDTO itemResponseDTO);

    ItemResponseDTO toItemResponseDTO(Item item);

    ItemResponseDTO toItemResponseDTO(ItemDTO itemDTO);
}
