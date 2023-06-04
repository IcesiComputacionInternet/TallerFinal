package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.CategoryShowDTO;
import co.icesi.automoviles.dto.CustomerCreateDTO;
import co.icesi.automoviles.dto.CustomerShowDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import co.icesi.automoviles.dto.RoleShowDTO;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.model.Customer;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.model.PurchaseOrder;
import co.icesi.automoviles.model.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-04T15:22:35-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230511-1142, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer fromCustomerCreateDTO(CustomerCreateDTO customerCreateDTO) {
        if ( customerCreateDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.address( customerCreateDTO.getAddress() );
        customer.birthDate( customerCreateDTO.getBirthDate() );
        customer.email( customerCreateDTO.getEmail() );
        customer.firstName( customerCreateDTO.getFirstName() );
        customer.lastName( customerCreateDTO.getLastName() );
        customer.password( customerCreateDTO.getPassword() );
        customer.phoneNumber( customerCreateDTO.getPhoneNumber() );
        customer.role( roleShowDTOToRole( customerCreateDTO.getRole() ) );

        return customer.build();
    }

    @Override
    public CustomerCreateDTO fromCustomerToCustomerCreateDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerCreateDTO.CustomerCreateDTOBuilder customerCreateDTO = CustomerCreateDTO.builder();

        customerCreateDTO.address( customer.getAddress() );
        customerCreateDTO.birthDate( customer.getBirthDate() );
        customerCreateDTO.email( customer.getEmail() );
        customerCreateDTO.firstName( customer.getFirstName() );
        customerCreateDTO.lastName( customer.getLastName() );
        customerCreateDTO.password( customer.getPassword() );
        customerCreateDTO.phoneNumber( customer.getPhoneNumber() );
        customerCreateDTO.role( roleToRoleShowDTO( customer.getRole() ) );

        return customerCreateDTO.build();
    }

    @Override
    public Role fromCustomerShowDTO(CustomerShowDTO customerShowDTO) {
        if ( customerShowDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        return role.build();
    }

    @Override
    public CustomerShowDTO fromCustomerToCustomerShowDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerShowDTO.CustomerShowDTOBuilder customerShowDTO = CustomerShowDTO.builder();

        customerShowDTO.address( customer.getAddress() );
        customerShowDTO.birthDate( customer.getBirthDate() );
        customerShowDTO.customerId( customer.getCustomerId() );
        customerShowDTO.email( customer.getEmail() );
        customerShowDTO.firstName( customer.getFirstName() );
        customerShowDTO.lastName( customer.getLastName() );
        customerShowDTO.orders( purchaseOrderListToPurchaseOrderShowDTOList( customer.getOrders() ) );
        customerShowDTO.phoneNumber( customer.getPhoneNumber() );
        customerShowDTO.role( roleToRoleShowDTO( customer.getRole() ) );

        return customerShowDTO.build();
    }

    protected List<Item> itemShowDTOListToItemList(List<ItemShowDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Item> list1 = new ArrayList<Item>( list.size() );
        for ( ItemShowDTO itemShowDTO : list ) {
            list1.add( itemShowDTOToItem( itemShowDTO ) );
        }

        return list1;
    }

    protected Category categoryShowDTOToCategory(CategoryShowDTO categoryShowDTO) {
        if ( categoryShowDTO == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.categoryId( categoryShowDTO.getCategoryId() );
        category.description( categoryShowDTO.getDescription() );
        category.items( itemShowDTOListToItemList( categoryShowDTO.getItems() ) );
        category.name( categoryShowDTO.getName() );

        return category.build();
    }

    protected List<PurchaseOrder> purchaseOrderShowDTOListToPurchaseOrderList(List<PurchaseOrderShowDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<PurchaseOrder> list1 = new ArrayList<PurchaseOrder>( list.size() );
        for ( PurchaseOrderShowDTO purchaseOrderShowDTO : list ) {
            list1.add( purchaseOrderShowDTOToPurchaseOrder( purchaseOrderShowDTO ) );
        }

        return list1;
    }

    protected Item itemShowDTOToItem(ItemShowDTO itemShowDTO) {
        if ( itemShowDTO == null ) {
            return null;
        }

        Item.ItemBuilder item = Item.builder();

        item.category( categoryShowDTOToCategory( itemShowDTO.getCategory() ) );
        item.description( itemShowDTO.getDescription() );
        item.imageUrl( itemShowDTO.getImageUrl() );
        item.itemId( itemShowDTO.getItemId() );
        item.name( itemShowDTO.getName() );
        item.price( itemShowDTO.getPrice() );
        item.purchaseOrders( purchaseOrderShowDTOListToPurchaseOrderList( itemShowDTO.getPurchaseOrders() ) );

        return item.build();
    }

    protected PurchaseOrder purchaseOrderShowDTOToPurchaseOrder(PurchaseOrderShowDTO purchaseOrderShowDTO) {
        if ( purchaseOrderShowDTO == null ) {
            return null;
        }

        PurchaseOrder.PurchaseOrderBuilder purchaseOrder = PurchaseOrder.builder();

        purchaseOrder.customer( customerShowDTOToCustomer( purchaseOrderShowDTO.getCustomer() ) );
        purchaseOrder.items( itemShowDTOListToItemList( purchaseOrderShowDTO.getItems() ) );
        purchaseOrder.purchaseOrderId( purchaseOrderShowDTO.getPurchaseOrderId() );
        purchaseOrder.status( purchaseOrderShowDTO.getStatus() );
        purchaseOrder.total( purchaseOrderShowDTO.getTotal() );

        return purchaseOrder.build();
    }

    protected Customer customerShowDTOToCustomer(CustomerShowDTO customerShowDTO) {
        if ( customerShowDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.address( customerShowDTO.getAddress() );
        customer.birthDate( customerShowDTO.getBirthDate() );
        customer.customerId( customerShowDTO.getCustomerId() );
        customer.email( customerShowDTO.getEmail() );
        customer.firstName( customerShowDTO.getFirstName() );
        customer.lastName( customerShowDTO.getLastName() );
        customer.orders( purchaseOrderShowDTOListToPurchaseOrderList( customerShowDTO.getOrders() ) );
        customer.phoneNumber( customerShowDTO.getPhoneNumber() );
        customer.role( roleShowDTOToRole( customerShowDTO.getRole() ) );

        return customer.build();
    }

    protected List<Customer> customerShowDTOListToCustomerList(List<CustomerShowDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Customer> list1 = new ArrayList<Customer>( list.size() );
        for ( CustomerShowDTO customerShowDTO : list ) {
            list1.add( customerShowDTOToCustomer( customerShowDTO ) );
        }

        return list1;
    }

    protected Role roleShowDTOToRole(RoleShowDTO roleShowDTO) {
        if ( roleShowDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.customers( customerShowDTOListToCustomerList( roleShowDTO.getCustomers() ) );
        role.description( roleShowDTO.getDescription() );
        role.roleId( roleShowDTO.getRoleId() );
        role.roleName( roleShowDTO.getRoleName() );

        return role.build();
    }

    protected List<CustomerShowDTO> customerListToCustomerShowDTOList(List<Customer> list) {
        if ( list == null ) {
            return null;
        }

        List<CustomerShowDTO> list1 = new ArrayList<CustomerShowDTO>( list.size() );
        for ( Customer customer : list ) {
            list1.add( fromCustomerToCustomerShowDTO( customer ) );
        }

        return list1;
    }

    protected RoleShowDTO roleToRoleShowDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleShowDTO.RoleShowDTOBuilder roleShowDTO = RoleShowDTO.builder();

        roleShowDTO.customers( customerListToCustomerShowDTOList( role.getCustomers() ) );
        roleShowDTO.description( role.getDescription() );
        roleShowDTO.roleId( role.getRoleId() );
        roleShowDTO.roleName( role.getRoleName() );

        return roleShowDTO.build();
    }

    protected List<ItemShowDTO> itemListToItemShowDTOList(List<Item> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemShowDTO> list1 = new ArrayList<ItemShowDTO>( list.size() );
        for ( Item item : list ) {
            list1.add( itemToItemShowDTO( item ) );
        }

        return list1;
    }

    protected CategoryShowDTO categoryToCategoryShowDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryShowDTO.CategoryShowDTOBuilder categoryShowDTO = CategoryShowDTO.builder();

        categoryShowDTO.categoryId( category.getCategoryId() );
        categoryShowDTO.description( category.getDescription() );
        categoryShowDTO.items( itemListToItemShowDTOList( category.getItems() ) );
        categoryShowDTO.name( category.getName() );

        return categoryShowDTO.build();
    }

    protected List<PurchaseOrderShowDTO> purchaseOrderListToPurchaseOrderShowDTOList(List<PurchaseOrder> list) {
        if ( list == null ) {
            return null;
        }

        List<PurchaseOrderShowDTO> list1 = new ArrayList<PurchaseOrderShowDTO>( list.size() );
        for ( PurchaseOrder purchaseOrder : list ) {
            list1.add( purchaseOrderToPurchaseOrderShowDTO( purchaseOrder ) );
        }

        return list1;
    }

    protected ItemShowDTO itemToItemShowDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemShowDTO.ItemShowDTOBuilder itemShowDTO = ItemShowDTO.builder();

        itemShowDTO.category( categoryToCategoryShowDTO( item.getCategory() ) );
        itemShowDTO.description( item.getDescription() );
        itemShowDTO.imageUrl( item.getImageUrl() );
        itemShowDTO.itemId( item.getItemId() );
        itemShowDTO.name( item.getName() );
        itemShowDTO.price( item.getPrice() );
        itemShowDTO.purchaseOrders( purchaseOrderListToPurchaseOrderShowDTOList( item.getPurchaseOrders() ) );

        return itemShowDTO.build();
    }

    protected PurchaseOrderShowDTO purchaseOrderToPurchaseOrderShowDTO(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }

        PurchaseOrderShowDTO.PurchaseOrderShowDTOBuilder purchaseOrderShowDTO = PurchaseOrderShowDTO.builder();

        purchaseOrderShowDTO.customer( fromCustomerToCustomerShowDTO( purchaseOrder.getCustomer() ) );
        purchaseOrderShowDTO.items( itemListToItemShowDTOList( purchaseOrder.getItems() ) );
        purchaseOrderShowDTO.purchaseOrderId( purchaseOrder.getPurchaseOrderId() );
        purchaseOrderShowDTO.status( purchaseOrder.getStatus() );
        purchaseOrderShowDTO.total( purchaseOrder.getTotal() );

        return purchaseOrderShowDTO.build();
    }
}
