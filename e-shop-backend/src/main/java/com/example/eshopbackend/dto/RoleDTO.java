package com.example.eshopbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDTO {
    private String roleId;
    private String roleName;
    private String description;
}
