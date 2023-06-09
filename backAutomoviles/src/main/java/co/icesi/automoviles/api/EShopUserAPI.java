package co.icesi.automoviles.api;

import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.dto.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.icesi.automoviles.dto.EShopUserCreateDTO;

import javax.validation.Valid;


@RequestMapping("/eShopUsers")
public interface EShopUserAPI {

    public static final String ROOT_PATH = "/eShopUsers";

    @PostMapping
    public EShopUserShowDTO registerEShopUser(@Valid @RequestBody EShopUserCreateDTO EShopUserCreateDTO);

    @PatchMapping("{eShopUserId}/role/{roleName}")
    public EShopUserShowDTO assignRole (@PathVariable("eShopUserId") String eShopUserId, @PathVariable("roleName") String roleName);

    @GetMapping
    public ResponseEntity<PageResponse<EShopUserShowDTO>> getAllUsers(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "per_page", defaultValue = "5") Integer perPage,
            @RequestParam(name = "sort", defaultValue = "eShopUserId") String sortBy,
            @RequestParam(name = "sort_dir", defaultValue = "asc") String sortDirection);
}
