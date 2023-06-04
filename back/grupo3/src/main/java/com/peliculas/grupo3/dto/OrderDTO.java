package com.peliculas.grupo3.dto;


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

    private UserDTO user;

    private String orderNumber;

    private List<MovieDTO> movies;
}
