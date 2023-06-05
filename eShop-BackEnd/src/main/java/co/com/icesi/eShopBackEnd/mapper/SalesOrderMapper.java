package co.com.icesi.eShopBackEnd.mapper;

import co.com.icesi.eShopBackEnd.dto.CreateSalesOderDTO;
import co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse.ResponseSalesOrderDTO;
import co.com.icesi.eShopBackEnd.model.SalesOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalesOrderMapper {
    @Mapping(target = "customer", source = "customer",ignore = true)
    @Mapping(target = "items", source = "items",ignore = true)
    SalesOrder fromCreateSalesOrderDTO(CreateSalesOderDTO salesOderDTO);

    @Mapping(target = "customer", source = "customer",ignore = true)
    @Mapping(target = "items", source = "items",ignore = true)
    CreateSalesOderDTO fromSalesOrder(SalesOrder salesOrder);

    ResponseSalesOrderDTO fromSalesOrderToResponse(SalesOrder salesOrder);
}
