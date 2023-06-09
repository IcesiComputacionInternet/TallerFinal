package co.com.icesi.backend.mapper;

import co.com.icesi.backend.dto.request.RequestNewOrderDTO;
import co.com.icesi.backend.dto.response.ResponseOrderDTO;
import co.com.icesi.backend.model.ShopOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "items", source = "items", ignore=true)
    ShopOrder fromRequestOrderDTO(RequestNewOrderDTO requestOrderDTO);
    @Mapping(target = "shopUser", ignore=true)
    @Mapping(target = "message", source = "message")
    ResponseOrderDTO fromOrderToResponseOrderDTO(ShopOrder shopOrder, String message);
    @Mapping(target = "shopUser", ignore=true)
    @Mapping(target = "message", source = "message")
    ResponseOrderDTO fromRequestChangeToResponseOrderDTO(ShopOrder shopOrder, String message);
}
