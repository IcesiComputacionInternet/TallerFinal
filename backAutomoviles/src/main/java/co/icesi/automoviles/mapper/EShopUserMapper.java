package co.icesi.automoviles.mapper;
import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.dto.EShopUserShowDTOForPurchaseOrder;
import co.icesi.automoviles.model.EShopUser;
import org.mapstruct.Mapper;

import co.icesi.automoviles.dto.EShopUserCreateDTO;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EShopUserMapper {

    EShopUser fromEShopUserCreateDTO(EShopUserCreateDTO EShopUserCreateDTO);
    EShopUserCreateDTO fromEShopUserToEShopUserCreateDTO(EShopUser eShopUser);
    EShopUserShowDTO fromEShopUserToEShopUserShowDTO(EShopUser eShopUser);
    @Mapping(source = "EShopUserId", target = "eShopUserId")
    EShopUserShowDTOForPurchaseOrder fromEShopUserToEShopUserShowDTOForPurchaseOrder(EShopUser eShopUser);
}
