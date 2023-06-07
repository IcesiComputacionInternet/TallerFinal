package com.peliculas.grupo3.dto;


import com.peliculas.grupo3.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String status;

    private long total;

    private UserResponseDTO user;

    private String orderNumber;

    private List<MovieDTO> movies;
}
