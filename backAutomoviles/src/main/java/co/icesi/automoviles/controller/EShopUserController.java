package co.icesi.automoviles.controller;

import javax.validation.Valid;

import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.dto.EShopUserCreateDTO;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.automoviles.api.EShopUserAPI;
import co.icesi.automoviles.service.EShopUserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EShopUserController implements EShopUserAPI {
    
    private final EShopUserService EShopUserService;

    @Override
    public EShopUserShowDTO registerEShopUser(@Valid EShopUserCreateDTO EShopUserCreateDTO) {
        return EShopUserService.registerEShopUser(EShopUserCreateDTO);
    }

    @Override
    public EShopUserShowDTO assignRole(String eShopUserId, String roleName) {
        return EShopUserService.assignRole(eShopUserId, roleName);
    }

    
}
