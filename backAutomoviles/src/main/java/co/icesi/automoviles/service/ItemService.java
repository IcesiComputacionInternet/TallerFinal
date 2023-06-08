package co.icesi.automoviles.service;

import co.icesi.automoviles.dto.CategoryShowDTOForItem;
import co.icesi.automoviles.dto.ItemCreateDTO;
import co.icesi.automoviles.dto.ItemShowDTO;
import co.icesi.automoviles.error.exception.DetailBuilder;
import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.util.ShopExceptionBuilder;
import co.icesi.automoviles.mapper.CategoryMapper;
import co.icesi.automoviles.mapper.ItemMapper;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.repository.CategoryRepository;
import co.icesi.automoviles.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;
    private final CategoryMapper categoryMapper;
    public ItemShowDTO createItem(ItemCreateDTO itemCreateDTO){
        Category category = getCategory(itemCreateDTO.getCategoryUUID());
        CategoryShowDTOForItem categoryShowDTOForItem = categoryMapper.fromCategoryToCategoryShowDTOFromItem(category);
        Item item = itemMapper.fromItemCreateDTOToItem(itemCreateDTO);
        item.setItemId(UUID.randomUUID());
        item.setCategory(category);
        ItemShowDTO itemShowDTO = itemMapper.fromItemToItemShowDTO(itemRepository.save(item));
        itemShowDTO.setCategory(categoryShowDTOForItem);
        return itemShowDTO;
    }

    public ItemShowDTO updateItem(String itemId, ItemCreateDTO itemCreateDTO){
        Item item = getItem(itemId);
        Category category = getCategory(itemCreateDTO.getCategoryUUID());
        CategoryShowDTOForItem categoryShowDTOForItem = categoryMapper.fromCategoryToCategoryShowDTOFromItem(category);
        Item itemUpdated = itemMapper.fromItemCreateDTOToItem(itemCreateDTO);
        itemUpdated.setItemId(item.getItemId());
        itemUpdated.setCategory(category);
        ItemShowDTO itemShowDTO = itemMapper.fromItemToItemShowDTO(itemRepository.save(itemUpdated));
        itemShowDTO.setCategory(categoryShowDTOForItem);
        return itemShowDTO;
    }

    public Category getCategory(String categoryUUID){
        return categoryRepository.findById(UUID.fromString(categoryUUID)).orElseThrow(
                ShopExceptionBuilder.createShopException(
                        "category with the id: " + categoryUUID + " not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "category", "id", categoryUUID))
        );
    }

    public Item getItem(String itemUUID){
        return itemRepository.findById(UUID.fromString(itemUUID)).orElseThrow(
                ShopExceptionBuilder.createShopException(
                        "item with the id: " + itemUUID + " not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "category", "id", itemUUID))
        );
    }
}
