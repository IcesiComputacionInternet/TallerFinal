package co.icesi.automoviles.mapper;
import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.model.EShopUser;
import org.mapstruct.Mapper;

import co.icesi.automoviles.dto.EShopUserCreateDTO;


@Mapper(componentModel = "spring")
public interface EShopUserMapper {

    EShopUser fromEShopUserCreateDTO(EShopUserCreateDTO EShopUserCreateDTO);
    EShopUserCreateDTO fromEShopUserToEShopUserCreateDTO(EShopUser eShopUser);
    EShopUserShowDTO fromEShopUserToEShopUserShowDTO(EShopUser eShopUser);

}
