package com.example.eshopbackend.api;

import com.example.eshopbackend.dto.RoleDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(RoleApi.ROLE_BASE_URL)
public interface RoleApi {
    String ROLE_BASE_URL = "/roles";

    @PostMapping("/add")
    RoleDTO createRole(@RequestBody @Valid RoleDTO roleDTO);

    @GetMapping("/getAll")
    List<RoleDTO> getAll();
}
