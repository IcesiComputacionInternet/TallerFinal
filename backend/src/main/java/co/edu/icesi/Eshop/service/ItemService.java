package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.error.exception.DetailBuilder;
import co.edu.icesi.Eshop.error.exception.ErrorCode;
import co.edu.icesi.Eshop.mapper.ItemMapper;
import co.edu.icesi.Eshop.model.Category;
import co.edu.icesi.Eshop.model.Item;
import co.edu.icesi.Eshop.model.Roles;
import co.edu.icesi.Eshop.repository.CategoryRepository;
import co.edu.icesi.Eshop.repository.ItemRepository;
import co.edu.icesi.Eshop.security.EShopSecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static co.edu.icesi.Eshop.error.util.EShopExceptionBuilder.createEShopException;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final CategoryRepository categoryRepository;

    private final ItemMapper itemMapper;

    public ItemDTO save(ItemDTO itemDTO){

        checkAuthorization();
        verifyItemName(itemDTO.getName());

        Category category = searchCategory(itemDTO.getCategory());
        Item item = itemMapper.fromItemDTO(itemDTO);
        item.setItemId(UUID.randomUUID());
        item.setCategory(category);

        return itemMapper.fromItem(itemRepository.save(item));
    }

    public ItemDTO setItemState(String itemName){

        adminAuthorizationOnly();
        Item item = getItem(itemName);
        item.setAvailable(!item.isAvailable());
        itemRepository.save(item);

        return itemMapper.fromItem(item);
    }

    private void verifyItemName(String itemName){
        if (itemRepository.findByName(itemName).isPresent()){
            throw createEShopException(
                    "Duplicated item name",
                    HttpStatus.CONFLICT,
                    new DetailBuilder(ErrorCode.ERR_DUPLICATED, "Item with name", itemName)
            ).get();
        }
    }

    public List<ItemDTO> getAllItems(){
        adminAuthorizationOnly();
        return itemRepository.findAll().stream().map(itemMapper::fromItem).toList();
    }

    public void checkAuthorization(){
        String currentUserRole = EShopSecurityContext.getCurrentUserRole();

        if (currentUserRole.equalsIgnoreCase(String.valueOf(Roles.USER))){
            throw createEShopException(
                    "Unauthorized",
                    HttpStatus.UNAUTHORIZED,
                    new DetailBuilder(ErrorCode.ERR_LOGIN,"You are not authorized")
            ).get();
        }
    }

    public void adminAuthorizationOnly(){
        String currentUserRole = EShopSecurityContext.getCurrentUserRole();

        if (!currentUserRole.equalsIgnoreCase(String.valueOf(Roles.ADMIN))){
            throw createEShopException(
                    "Unauthorized",
                    HttpStatus.UNAUTHORIZED,
                    new DetailBuilder(ErrorCode.ERR_LOGIN,"You are not authorized")
            ).get();
        }
    }

    public ItemDTO getItemByName(String itemName){
        return itemMapper.fromItem(itemRepository.findByName(itemName).orElseThrow(createEShopException("Item not found",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "Item with name",itemName)
        )));
    }

    private Item getItem(String itemName){
        return itemRepository.findByName(itemName).orElseThrow(createEShopException("Item not found",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "Item with name",itemName)
        ));
    }

    private Category searchCategory(String categoryName){
        return categoryRepository.findByName(categoryName).orElseThrow(createEShopException("Category not found",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "Category with name",categoryName)
        ));
    }
}
