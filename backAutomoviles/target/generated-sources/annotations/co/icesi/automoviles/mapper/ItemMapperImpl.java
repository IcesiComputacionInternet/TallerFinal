package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.ItemCreateDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.model.Item;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-08T18:09:05-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item fromItemCreateDTOToItem(ItemCreateDTO itemCreateDTO) {
        if ( itemCreateDTO == null ) {
            return null;
        }

        Item.ItemBuilder item = Item.builder();

        item.description( itemCreateDTO.getDescription() );
        item.name( itemCreateDTO.getName() );
        item.price( itemCreateDTO.getPrice() );
        item.imageUrl( itemCreateDTO.getImageUrl() );

        return item.build();
    }

    @Override
    public ItemShowDTO fromItemToItemShowDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemShowDTO.ItemShowDTOBuilder itemShowDTO = ItemShowDTO.builder();

        itemShowDTO.itemId( item.getItemId() );
        itemShowDTO.description( item.getDescription() );
        itemShowDTO.name( item.getName() );
        itemShowDTO.price( item.getPrice() );
        itemShowDTO.imageUrl( item.getImageUrl() );

        return itemShowDTO.build();
    }
}
