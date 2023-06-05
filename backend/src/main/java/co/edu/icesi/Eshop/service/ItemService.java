package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.error.exception.DetailBuilder;
import co.edu.icesi.Eshop.error.exception.ErrorCode;
import co.edu.icesi.Eshop.mapper.ItemMapper;
import co.edu.icesi.Eshop.model.Item;
import co.edu.icesi.Eshop.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static co.edu.icesi.Eshop.error.util.EShopExceptionBuilder.createEShopException;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemDTO save(ItemDTO itemDTO){

        verifyItemName(itemDTO.getName());
        verifyLevelOfEfficiency(itemDTO.getLevelOfEfficiency());

        Item item = itemMapper.fromItemDTO(itemDTO);
        item.setItemId(UUID.randomUUID());

        return itemMapper.fromItem(itemRepository.save(item));
    }

    public ItemDTO setItemState(String itemName){

        ItemDTO item = getItemByName(itemName);
        item.setAvailable(!item.isAvailable());

        itemRepository.save(itemMapper.fromItemDTO(item));

        return item;
    }

    public ItemDTO setItemPrice(String itemName, Long newPrice){

        ItemDTO itemDTO = getItemByName(itemName);
        itemDTO.setPrice(newPrice);

        itemRepository.save(itemMapper.fromItemDTO(itemDTO));

        return itemDTO;
    }

    private void verifyLevelOfEfficiency(String levelOfEfficiency){
        levelOfEfficiency = levelOfEfficiency.toUpperCase(Locale.ENGLISH);
        String rule = "[A-G]";

        if (!levelOfEfficiency.matches(rule)){
            throw createEShopException(
                    "Level of efficiency invalid",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_400, "Level of efficiency "+levelOfEfficiency, "invalid")
            ).get();
        }
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

    private void adminAuthorizationOnly(){
        //Validar que el rol es un admin
        if (true){

        }
    }

    public ItemDTO getItemByName(String itemName){
        return itemMapper.fromItem(itemRepository.findByName(itemName).orElseThrow(createEShopException("Item not found",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "Item with name",itemName)
        )));
    }
}
