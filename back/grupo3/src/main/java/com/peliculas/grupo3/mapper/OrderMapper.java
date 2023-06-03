package com.peliculas.grupo3.mapper;

import com.peliculas.grupo3.dto.OrderDTO;
import com.peliculas.grupo3.model.MovieOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    MovieOrder fromOrderDTO(OrderDTO orderDTO);
}
