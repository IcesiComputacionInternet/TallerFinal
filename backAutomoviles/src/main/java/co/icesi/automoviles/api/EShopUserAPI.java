package co.icesi.automoviles.api;

import co.icesi.automoviles.dto.EShopUserShowDTO;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.icesi.automoviles.dto.EShopUserCreateDTO;

import javax.validation.Valid;


@RequestMapping("/eShopUsers")
public interface EShopUserAPI {

    public static final String ROOT_PATH = "/eShopUsers";

    @PostMapping
    public EShopUserShowDTO registerEShopUser(@Valid @RequestBody EShopUserCreateDTO EShopUserCreateDTO);

    @PatchMapping("{eShopUserId}/role/{roleName}")
    public EShopUserShowDTO assignRole (@PathVariable("eShopUserId") String eShopUserId, @PathVariable("roleName") String roleName);

}
