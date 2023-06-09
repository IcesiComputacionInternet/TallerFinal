package co.com.icesi.eShopBackEnd.unit.service;

import co.com.icesi.eShopBackEnd.dto.CreateItemDTO;
import co.com.icesi.eShopBackEnd.dto.CreateRoleDTO;
import co.com.icesi.eShopBackEnd.dto.CreateSalesOderDTO;
import co.com.icesi.eShopBackEnd.dto.response.salesOrderResponse.ResponseSalesOrderDTO;
import co.com.icesi.eShopBackEnd.mapper.RoleMapper;
import co.com.icesi.eShopBackEnd.mapper.SalesOrderMapper;
import co.com.icesi.eShopBackEnd.mapper.SalesOrderMapperImpl;
import co.com.icesi.eShopBackEnd.model.*;
import co.com.icesi.eShopBackEnd.repository.CustomerRepository;
import co.com.icesi.eShopBackEnd.repository.ItemRepository;
import co.com.icesi.eShopBackEnd.repository.RoleRepository;
import co.com.icesi.eShopBackEnd.repository.SalesOrderRepository;
import co.com.icesi.eShopBackEnd.service.RoleService;
import co.com.icesi.eShopBackEnd.service.SalesOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SalesOrderServiceTest {
    private  SalesOrderRepository salesOrderRepository;
    private  CustomerRepository customerRepository;
    private  ItemRepository itemRepository;
    private  SalesOrderMapper salesOrderMapper;
    private SalesOrderService salesOrderService;
    @BeforeEach
    public void init(){
        salesOrderRepository = mock(SalesOrderRepository.class);
        customerRepository = mock(CustomerRepository.class);
        itemRepository = mock(ItemRepository.class);
        salesOrderMapper = spy(SalesOrderMapperImpl.class);
        salesOrderService = new SalesOrderService(salesOrderRepository, customerRepository,itemRepository,salesOrderMapper);
    }
    @Test
    public void testCreateSalesOrderServiceWhenCustomerNotExist() {
        try {
            salesOrderService.save(defaultSalesOderDTO());
        } catch (RuntimeException exception) {
            assertEquals("Not existing data", exception.getMessage());
        }

    }
    @Test
    public void testCreateSalesOrderServiceWhenItemsNotExist() {
        try{
        when(customerRepository.findUserByEmail(any())).thenReturn(Optional.of(defaultCustomer()));
        salesOrderService.save(defaultSalesOderDTO());
    } catch (RuntimeException exception) {
        assertEquals("Not existing data", exception.getMessage());
    }

    }
    @Test
    public void testCreateSalesOrderService() {
        // Mocking customer repository
        when(customerRepository.findUserByEmail(any())).thenReturn(Optional.of(defaultCustomer()));

        // Mocking item repository
        List<Item> items = Arrays.asList(defaultItem());
        when(itemRepository.returnItem(any())).thenReturn(Optional.of(defaultItem()));

        SalesOrder salesOrder = defaulSalesOrder();
        when(salesOrderMapper.fromCreateSalesOrderDTO(any())).thenReturn(salesOrder);
        when(salesOrderRepository.save(any())).thenReturn(salesOrder);
        ResponseSalesOrderDTO responseDTO = salesOrderService.save(defaultSalesOderDTO());
        assertEquals("TV", responseDTO.getItems().get(0).getName());
    }


    private SalesOrder defaulSalesOrder() {
        return SalesOrder.builder().total(500L).customer(defaultCustomer()).items(itemsI()).build();
    }

    private CreateSalesOderDTO defaultSalesOderDTO() {
        return CreateSalesOderDTO.builder().customer("luis").items(items()).build();
    }

    private List<CreateItemDTO> items() {
        List<CreateItemDTO> itemList = new ArrayList<>();


        CreateItemDTO item1= CreateItemDTO.builder().name("TV").price(2000L).category("TV").brand("password").stock(3).imageURL("tv.com").description("TV").build();
        itemList.add(item1);

        return itemList;
    }
    private List<Item> itemsI() {
        List<Item> itemList = new ArrayList<>();


        Item item1= Item.builder().name("TV").price(2000L).category(defaulCategory()).brand("password").stock(3).imageURL("tv.com").description("TV").build();
        itemList.add(item1);

        return itemList;
    }

    private Customer defaultCustomer() {
        return Customer.builder().firstName("luis").lastName("andres").email("lucho@email.com").password("password").phoneNumber("332036584").address("call 65 Nº 32").birthday(LocalDate.parse("2003-06-06")).role(defaultRole()).build();
    }
    private Role defaultRole() {
        return Role.builder().roleName("USER").description("Is a teacher the university Icesi").build();
    }
    private Item defaultItem() {
        return Item.builder().name("TV").price(2000L).category(defaulCategory()).brand("password").stock(3).imageURL("tv.com").description("TV").build();
    }
    private Category defaulCategory() {
        return Category.builder().name("TV").description("TV").build();
    }
}
