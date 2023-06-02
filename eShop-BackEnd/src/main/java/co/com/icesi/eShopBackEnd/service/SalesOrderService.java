package co.com.icesi.eShopBackEnd.service;

import co.com.icesi.eShopBackEnd.Enum.OrderState;
import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
import co.com.icesi.eShopBackEnd.dto.CreateSalesOderDTO;
import co.com.icesi.eShopBackEnd.dto.UpdateOrderStateDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseSalesOrderDTO;
import co.com.icesi.eShopBackEnd.error.enums.ErrorCode;
import co.com.icesi.eShopBackEnd.error.util.ArgumentsExceptionBuilder;
import co.com.icesi.eShopBackEnd.error.util.DetailBuilder;
import co.com.icesi.eShopBackEnd.mapper.CustomerMapper;
import co.com.icesi.eShopBackEnd.mapper.ItemMapper;
import co.com.icesi.eShopBackEnd.mapper.SalesOrderMapper;
import co.com.icesi.eShopBackEnd.model.Customer;
import co.com.icesi.eShopBackEnd.model.Item;
import co.com.icesi.eShopBackEnd.model.SalesOrder;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
import co.com.icesi.eShopBackEnd.repository.ItemRepository;
import co.com.icesi.eShopBackEnd.repository.SalesOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final SalesOrderMapper salesOrderMapper;
    private final CustomerMapper customerMapper;
    private final ItemMapper itemMapper;


    public ResponseSalesOrderDTO save(CreateSalesOderDTO createDTO){

        Customer customer = customerRepository.findUserByEmail(createDTO.customer()).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"user")
                )
        );

        List<Item> items = validateItems(createDTO.items());

        SalesOrder newOrder = salesOrderMapper.fromCreateSalesOrderDTO(createDTO);
        newOrder.setOrderId(UUID.randomUUID());
        newOrder.setStatus(OrderState.CREATED);
        newOrder.setTotal(calculateTotal(items));
        newOrder.setCustomer(customer);
        newOrder.setItems(items);

        return salesOrderMapper.fromSalesOrderToResponse(salesOrderRepository.save(newOrder));
    }

    private List<Item> validateItems(List<CreateItemDTO> items){
        return items.stream()
                .map(itemDTO -> itemRepository.returnItem(itemDTO.name()).orElseThrow(
                        ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                                "Not existing data",
                                HttpStatus.BAD_REQUEST,
                                new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"item")
                        )
                )).toList();
    }

    private Long calculateTotal(List<Item> items){
        return items.stream()
                .mapToLong(Item::getPrice)
                .sum();
    }

    public ResponseSalesOrderDTO updateOrderState(UpdateOrderStateDTO orderDTO){
        SalesOrder order = salesOrderRepository.returnOrder(orderDTO.orderId()).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"order")
                )
        );

        order.setStatus(orderDTO.status());
        salesOrderRepository.updateStateOrder(orderDTO.orderId(),orderDTO.status());
        return salesOrderMapper.fromSalesOrderToResponse(order);

    }
}
