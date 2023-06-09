package com.peliculas.grupo3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private String name;

    private String description;

    private long price;

    private String imageURL;

    private CategoryDTO categoryDTO;

    private String pgRating;

}
