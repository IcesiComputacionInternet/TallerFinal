package co.edu.icesi.Eshop.mapper;

import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.dto.OrderDTO;
import co.edu.icesi.Eshop.model.EShopOrder;
import co.edu.icesi.Eshop.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "items", source = "items",ignore=true)
    EShopOrder fromOrderDTO(OrderDTO orderDTO);

    @Mapping(target = "category", expression = "java(item.getCategory().getName())")
    ItemDTO fromItem(Item item);

   @Mapping(target = "items", expression = "java(order.getItems().stream().map(this::fromItem).collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "userEmail", expression = "java(order.getEShopUser().getEmail())")
    @Mapping(target = "userPhoneNumber", expression = "java(order.getEShopUser().getPhoneNumber())")
    OrderDTO fromOrder(EShopOrder order);


}
