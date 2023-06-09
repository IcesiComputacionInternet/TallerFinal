package co.icesi.automoviles.mapper;

import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.CategoryShowDTO;
import co.icesi.automoviles.dto.CategoryShowDTOForItem;
import co.icesi.automoviles.dto.ItemShowDTOForCategory;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.model.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-09T07:05:44-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category fromCategoryCreateDTOToCategory(CategoryCreateDTO categoryCreateDTO) {
        if ( categoryCreateDTO == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( categoryCreateDTO.getName() );
        category.description( categoryCreateDTO.getDescription() );

        return category.build();
    }

    @Override
    public CategoryShowDTO fromCategoryToCategoryShowDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryShowDTO.CategoryShowDTOBuilder categoryShowDTO = CategoryShowDTO.builder();

        categoryShowDTO.categoryId( category.getCategoryId() );
        categoryShowDTO.name( category.getName() );
        categoryShowDTO.description( category.getDescription() );
        categoryShowDTO.items( itemListToItemShowDTOForCategoryList( category.getItems() ) );

        return categoryShowDTO.build();
    }

    @Override
    public CategoryShowDTOForItem fromCategoryToCategoryShowDTOFromItem(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryShowDTOForItem.CategoryShowDTOForItemBuilder categoryShowDTOForItem = CategoryShowDTOForItem.builder();

        categoryShowDTOForItem.categoryId( category.getCategoryId() );
        categoryShowDTOForItem.name( category.getName() );
        categoryShowDTOForItem.description( category.getDescription() );

        return categoryShowDTOForItem.build();
    }

    protected ItemShowDTOForCategory itemToItemShowDTOForCategory(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemShowDTOForCategory.ItemShowDTOForCategoryBuilder itemShowDTOForCategory = ItemShowDTOForCategory.builder();

        itemShowDTOForCategory.itemId( item.getItemId() );
        itemShowDTOForCategory.description( item.getDescription() );
        itemShowDTOForCategory.name( item.getName() );
        itemShowDTOForCategory.price( item.getPrice() );
        itemShowDTOForCategory.imageUrl( item.getImageUrl() );

        return itemShowDTOForCategory.build();
    }

    protected List<ItemShowDTOForCategory> itemListToItemShowDTOForCategoryList(List<Item> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemShowDTOForCategory> list1 = new ArrayList<ItemShowDTOForCategory>( list.size() );
        for ( Item item : list ) {
            list1.add( itemToItemShowDTOForCategory( item ) );
        }

        return list1;
    }
}
