package com.example.eshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private String roleId;

    @NotBlank(message = "Role name is required")
    private String roleName;

    @NotBlank(message = "Role description is required")
    private String description;
}
