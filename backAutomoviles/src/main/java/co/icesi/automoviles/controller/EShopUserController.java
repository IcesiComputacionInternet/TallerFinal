package co.icesi.automoviles.controller;

import javax.validation.Valid;

import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.dto.EShopUserCreateDTO;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.automoviles.api.EShopUserAPI;
import co.icesi.automoviles.security.ShopSecurityContext;
import co.icesi.automoviles.service.EShopUserMapperService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EShopUserController implements EShopUserAPI {
    
    private final EShopUserMapperService EShopUserMapperService;

    @Override
    public EShopUserShowDTO registerEShopUser(@Valid EShopUserCreateDTO EShopUserCreateDTO) {
        return EShopUserMapperService.registerEShopUser(EShopUserCreateDTO);
    }

    @Override
    public EShopUserShowDTO assignRole(String eShopUserId, String roleName) {
        return EShopUserMapperService.assignRole(eShopUserId, roleName);
    }

    
}
