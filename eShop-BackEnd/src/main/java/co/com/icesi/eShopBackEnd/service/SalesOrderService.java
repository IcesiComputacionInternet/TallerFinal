package co.com.icesi.eShopBackEnd.service;

import co.com.icesi.eShopBackEnd.Enum.OrderState;
import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
import co.com.icesi.eShopBackEnd.dto.CreateSalesOderDTO;
import co.com.icesi.eShopBackEnd.dto.UpdateOrderStateDTO;
import co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse.ResponseSalesOrderDTO;
import co.com.icesi.eShopBackEnd.error.enums.ErrorCode;
import co.com.icesi.eShopBackEnd.error.util.ArgumentsExceptionBuilder;
import co.com.icesi.eShopBackEnd.error.util.DetailBuilder;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final SalesOrderMapper salesOrderMapper;


    @Transactional
    public ResponseSalesOrderDTO save(CreateSalesOderDTO createDTO){

        Customer customer = customerRepository.findUserByEmail(createDTO.customer()).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"user")
                )
        );

        List<Item> items = validateItemsStock(validateItemsExists(createDTO.items()),createDTO.items());


        SalesOrder newOrder = salesOrderMapper.fromCreateSalesOrderDTO(createDTO);
        newOrder.setOrderId(UUID.randomUUID());
        newOrder.setStatus(OrderState.CREATED);
        newOrder.setTotal(calculateTotal(items,createDTO.items()));
        newOrder.setCustomer(customer);
        newOrder.setItems(items);

        //Update the stock of each item
        updateItemsStock(items,createDTO.items());
        System.out.println(salesOrderRepository.save(newOrder).getTotal() + "asdasd");
        return salesOrderMapper.fromSalesOrderToResponse(salesOrderRepository.save(newOrder));
    }

    @Transactional
    public void updateItemsStock(List<Item> itemsBD, List<CreateItemDTO> itemsDTO){
        itemsBD.forEach(item -> {

            CreateItemDTO itemDTO = itemsDTO.stream()
                    .filter(itemDTO1 -> itemDTO1.name().equals(item.getName()))
                    .findFirst()
                    .orElseThrow(
                            ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                                    "Not existing data",
                                    HttpStatus.BAD_REQUEST,
                                    new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"item")
                            )
                    );

            item.setStock(item.getStock() - itemDTO.stock());
            itemRepository.updateStock(item.getName(),item.getStock());

        });
    }

    private List<Item> validateItemsExists(List<CreateItemDTO> items){
        //Validate that the items exist
        return items.stream()
                .map(itemDTO -> itemRepository.returnItem(itemDTO.name()).orElseThrow(
                        ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                                "Not existing data",
                                HttpStatus.BAD_REQUEST,
                                new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"item")
                        )
                )).toList();
    }
    private List<Item> validateItemsStock(List<Item> itemsBD, List<CreateItemDTO> itemsDTO){
        return itemsBD.stream().peek(itemBD -> {

            CreateItemDTO itemDTO = itemsDTO.stream()
                    .filter(item -> item.name().equals(itemBD.getName()))
                    .findFirst()
                    .orElseThrow(
                            ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                                    "Not existing data",
                                    HttpStatus.BAD_REQUEST,
                                    new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"item")
                            )
                    );

            if(itemBD.getStock() < itemDTO.stock()){
                throw ArgumentsExceptionBuilder.createArgumentsException(
                        "Not enough stock",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_ENOUGH_STOCK,itemBD.getName())
                );
            }
        }).toList();

    }

    private Long calculateTotal(List<Item> itemsBD, List<CreateItemDTO> itemsDTO){
        //Calculate the total taking into account the quantity of each item
        return  itemsBD.stream().reduce(0L,(total,itemBD) -> {
            CreateItemDTO itemDTO = itemsDTO.stream()
                    .filter(item -> item.name().equals(itemBD.getName()))
                    .findFirst()
                    .orElseThrow(
                            ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                                    "Not existing data",
                                    HttpStatus.BAD_REQUEST,
                                    new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"item")
                            )
                    );
            return total + (itemBD.getPrice() * itemDTO.stock());
        },Long::sum);

    }

    public ResponseSalesOrderDTO getSalesOrderById(String salesOrderId){
        SalesOrder order = salesOrderRepository.returnOrder(UUID.fromString(salesOrderId)).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Not existing data",
                        HttpStatus.BAD_REQUEST,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"order")
                )
        );
        return salesOrderMapper.fromSalesOrderToResponse(order);
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

    public List<ResponseSalesOrderDTO> getAllOrders(){
        List<SalesOrder> orders = salesOrderRepository.findAll();
        return orders.stream().map(salesOrderMapper::fromSalesOrderToResponse).toList();
    }
}
