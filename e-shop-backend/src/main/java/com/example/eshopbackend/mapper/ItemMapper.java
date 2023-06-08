package com.example.eshopbackend.mapper;

import com.example.eshopbackend.dto.ItemDTO;
import com.example.eshopbackend.model.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item fromItemDTO(ItemDTO item);
    ItemDTO fromItem(Item item);
}
