package com.icesi.backend.mappers;

import com.icesi.backend.DTO.ItemCreateDTO;
import com.icesi.backend.models.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item fromItemCreateDTO(ItemCreateDTO itemCreateDTO);


}
