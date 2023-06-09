package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.CategoryShowDTOForItem;
import co.icesi.automoviles.dto.EShopUserCreateDTO;
import co.icesi.automoviles.dto.EShopUserShowDTO;
import co.icesi.automoviles.dto.EShopUserShowDTOForPurchaseOrder;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import co.icesi.automoviles.dto.RoleShowDTO;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.model.EShopUser;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.model.PurchaseOrder;
import co.icesi.automoviles.model.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-09T02:13:28-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class EShopUserMapperImpl implements EShopUserMapper {

    @Override
    public EShopUser fromEShopUserCreateDTO(EShopUserCreateDTO EShopUserCreateDTO) {
        if ( EShopUserCreateDTO == null ) {
            return null;
        }

        EShopUser.EShopUserBuilder eShopUser = EShopUser.builder();

        eShopUser.firstName( EShopUserCreateDTO.getFirstName() );
        eShopUser.lastName( EShopUserCreateDTO.getLastName() );
        eShopUser.email( EShopUserCreateDTO.getEmail() );
        eShopUser.phoneNumber( EShopUserCreateDTO.getPhoneNumber() );
        eShopUser.address( EShopUserCreateDTO.getAddress() );
        eShopUser.birthDate( EShopUserCreateDTO.getBirthDate() );
        eShopUser.password( EShopUserCreateDTO.getPassword() );
        eShopUser.role( roleShowDTOToRole( EShopUserCreateDTO.getRole() ) );

        return eShopUser.build();
    }

    @Override
    public EShopUserCreateDTO fromEShopUserToEShopUserCreateDTO(EShopUser eShopUser) {
        if ( eShopUser == null ) {
            return null;
        }

        EShopUserCreateDTO.EShopUserCreateDTOBuilder eShopUserCreateDTO = EShopUserCreateDTO.builder();

        eShopUserCreateDTO.firstName( eShopUser.getFirstName() );
        eShopUserCreateDTO.lastName( eShopUser.getLastName() );
        eShopUserCreateDTO.email( eShopUser.getEmail() );
        eShopUserCreateDTO.phoneNumber( eShopUser.getPhoneNumber() );
        eShopUserCreateDTO.address( eShopUser.getAddress() );
        eShopUserCreateDTO.birthDate( eShopUser.getBirthDate() );
        eShopUserCreateDTO.password( eShopUser.getPassword() );
        eShopUserCreateDTO.role( roleToRoleShowDTO( eShopUser.getRole() ) );

        return eShopUserCreateDTO.build();
    }

    @Override
    public EShopUserShowDTO fromEShopUserToEShopUserShowDTO(EShopUser eShopUser) {
        if ( eShopUser == null ) {
            return null;
        }

        EShopUserShowDTO.EShopUserShowDTOBuilder eShopUserShowDTO = EShopUserShowDTO.builder();

        eShopUserShowDTO.firstName( eShopUser.getFirstName() );
        eShopUserShowDTO.lastName( eShopUser.getLastName() );
        eShopUserShowDTO.email( eShopUser.getEmail() );
        eShopUserShowDTO.phoneNumber( eShopUser.getPhoneNumber() );
        eShopUserShowDTO.address( eShopUser.getAddress() );
        eShopUserShowDTO.birthDate( eShopUser.getBirthDate() );
        eShopUserShowDTO.orders( purchaseOrderListToPurchaseOrderShowDTOList( eShopUser.getOrders() ) );

        return eShopUserShowDTO.build();
    }

    @Override
    public EShopUserShowDTOForPurchaseOrder fromEShopUserToEShopUserShowDTOForPurchaseOrder(EShopUser eShopUser) {
        if ( eShopUser == null ) {
            return null;
        }

        EShopUserShowDTOForPurchaseOrder.EShopUserShowDTOForPurchaseOrderBuilder eShopUserShowDTOForPurchaseOrder = EShopUserShowDTOForPurchaseOrder.builder();

        eShopUserShowDTOForPurchaseOrder.eShopUserId( eShopUser.getEShopUserId() );
        eShopUserShowDTOForPurchaseOrder.firstName( eShopUser.getFirstName() );
        eShopUserShowDTOForPurchaseOrder.lastName( eShopUser.getLastName() );
        eShopUserShowDTOForPurchaseOrder.email( eShopUser.getEmail() );
        eShopUserShowDTOForPurchaseOrder.phoneNumber( eShopUser.getPhoneNumber() );
        eShopUserShowDTOForPurchaseOrder.address( eShopUser.getAddress() );
        eShopUserShowDTOForPurchaseOrder.birthDate( eShopUser.getBirthDate() );

        return eShopUserShowDTOForPurchaseOrder.build();
    }

    protected Role roleShowDTOToRole(RoleShowDTO roleShowDTO) {
        if ( roleShowDTO == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.roleId( roleShowDTO.getRoleId() );
        role.roleName( roleShowDTO.getRoleName() );
        role.description( roleShowDTO.getDescription() );

        return role.build();
    }

    protected RoleShowDTO roleToRoleShowDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleShowDTO.RoleShowDTOBuilder roleShowDTO = RoleShowDTO.builder();

        roleShowDTO.roleId( role.getRoleId() );
        roleShowDTO.roleName( role.getRoleName() );
        roleShowDTO.description( role.getDescription() );

        return roleShowDTO.build();
    }

    protected CategoryShowDTOForItem categoryToCategoryShowDTOForItem(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryShowDTOForItem.CategoryShowDTOForItemBuilder categoryShowDTOForItem = CategoryShowDTOForItem.builder();

        categoryShowDTOForItem.categoryId( category.getCategoryId() );
        categoryShowDTOForItem.name( category.getName() );
        categoryShowDTOForItem.description( category.getDescription() );

        return categoryShowDTOForItem.build();
    }

    protected ItemShowDTO itemToItemShowDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemShowDTO.ItemShowDTOBuilder itemShowDTO = ItemShowDTO.builder();

        itemShowDTO.itemId( item.getItemId() );
        itemShowDTO.description( item.getDescription() );
        itemShowDTO.name( item.getName() );
        itemShowDTO.price( item.getPrice() );
        itemShowDTO.imageUrl( item.getImageUrl() );
        itemShowDTO.category( categoryToCategoryShowDTOForItem( item.getCategory() ) );

        return itemShowDTO.build();
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

        purchaseOrderShowDTO.purchaseOrderId( purchaseOrder.getPurchaseOrderId() );
        purchaseOrderShowDTO.status( purchaseOrder.getStatus() );
        purchaseOrderShowDTO.total( purchaseOrder.getTotal() );
        purchaseOrderShowDTO.items( itemListToItemShowDTOList( purchaseOrder.getItems() ) );

        return purchaseOrderShowDTO.build();
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
}
