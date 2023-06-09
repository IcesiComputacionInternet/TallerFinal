package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.CategoryShowDTOForItem;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.dto.PurchaseOrderCreateDTO;
import co.icesi.automoviles.dto.PurchaseOrderShowDTO;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.model.PurchaseOrder;
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
public class PurchaseOrderMapperImpl implements PurchaseOrderMapper {

    @Override
    public PurchaseOrder fromPurchaseOrderCreateDTOToPurchaseOrder(PurchaseOrderCreateDTO purchaseOrderCreateDTO) {
        if ( purchaseOrderCreateDTO == null ) {
            return null;
        }

        PurchaseOrder.PurchaseOrderBuilder purchaseOrder = PurchaseOrder.builder();

        return purchaseOrder.build();
    }

    @Override
    public PurchaseOrderShowDTO fromPurchaseOrderToPurchaseOrderShowDTO(PurchaseOrder purchaseOrder) {
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
}
