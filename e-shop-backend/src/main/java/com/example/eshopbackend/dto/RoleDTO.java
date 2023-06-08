package com.example.eshopbackend.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class RoleDTO {
    private String roleId;

    @NotBlank(message = "Role name is required")
    private String roleName;

    @NotBlank(message = "Role description is required")
    private String description;
}
