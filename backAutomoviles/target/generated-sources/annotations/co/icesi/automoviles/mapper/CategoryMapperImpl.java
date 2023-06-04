package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.CategoryShowDTO;
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
    date = "2023-06-04T15:49:56-0500",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230511-1142, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category fromCategoryCreateDTOToCategory(CategoryCreateDTO categoryCreateDTO) {
        if ( categoryCreateDTO == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.description( categoryCreateDTO.getDescription() );
        category.name( categoryCreateDTO.getName() );

        return category.build();
    }

    @Override
    public CategoryShowDTO fromCategoryToCategoryShowDTO(Category category) {
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

    protected List<CustomerShowDTO> customerListToCustomerShowDTOList(List<Customer> list) {
        if ( list == null ) {
            return null;
        }

        List<CustomerShowDTO> list1 = new ArrayList<CustomerShowDTO>( list.size() );
        for ( Customer customer : list ) {
            list1.add( customerToCustomerShowDTO( customer ) );
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

    protected CustomerShowDTO customerToCustomerShowDTO(Customer customer) {
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

    protected PurchaseOrderShowDTO purchaseOrderToPurchaseOrderShowDTO(PurchaseOrder purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }

        PurchaseOrderShowDTO.PurchaseOrderShowDTOBuilder purchaseOrderShowDTO = PurchaseOrderShowDTO.builder();

        purchaseOrderShowDTO.customer( customerToCustomerShowDTO( purchaseOrder.getCustomer() ) );
        purchaseOrderShowDTO.items( itemListToItemShowDTOList( purchaseOrder.getItems() ) );
        purchaseOrderShowDTO.purchaseOrderId( purchaseOrder.getPurchaseOrderId() );
        purchaseOrderShowDTO.status( purchaseOrder.getStatus() );
        purchaseOrderShowDTO.total( purchaseOrder.getTotal() );

        return purchaseOrderShowDTO.build();
    }

    protected ItemShowDTO itemToItemShowDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemShowDTO.ItemShowDTOBuilder itemShowDTO = ItemShowDTO.builder();

        itemShowDTO.category( fromCategoryToCategoryShowDTO( item.getCategory() ) );
        itemShowDTO.description( item.getDescription() );
        itemShowDTO.imageUrl( item.getImageUrl() );
        itemShowDTO.itemId( item.getItemId() );
        itemShowDTO.name( item.getName() );
        itemShowDTO.price( item.getPrice() );
        itemShowDTO.purchaseOrders( purchaseOrderListToPurchaseOrderShowDTOList( item.getPurchaseOrders() ) );

        return itemShowDTO.build();
    }
}
