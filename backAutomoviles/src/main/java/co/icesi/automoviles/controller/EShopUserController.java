package co.icesi.automoviles.controller;

import javax.validation.Valid;

import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.dto.EShopUserCreateDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity<PageResponse<EShopUserShowDTO>> getAllUsers(Integer page, Integer perPage, String sortBy, String sortDirection) {
        int indexPage = page -1;
        Page<EShopUserShowDTO> eShopUserShowDTOS;
        eShopUserShowDTOS = EShopUserService.getAllUsers(indexPage, perPage, sortBy, sortDirection);
        PageResponse<EShopUserShowDTO> response = new PageResponse<>(eShopUserShowDTOS, new EShopUserShowDTO[0]);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
