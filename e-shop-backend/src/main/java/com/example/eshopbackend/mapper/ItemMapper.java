package com.example.eshopbackend.mapper;

import com.example.eshopbackend.dto.ItemDTO;
import com.example.eshopbackend.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target = "category", source = "category",ignore=true)
    Item fromItemDTO(ItemDTO item);
    @Mapping(target = "category", source = "category",ignore=true)
    ItemDTO fromItem(Item item);
}
