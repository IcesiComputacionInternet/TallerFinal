package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.mapper.ItemMapper;
import co.edu.icesi.Eshop.model.Item;
import co.edu.icesi.Eshop.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

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
            //Excepcion que diga que esta mal
        }
    }

    private void verifyItemName(String itemName){
        if (itemRepository.findByName(itemName).isPresent()){
            //Toca poner la excepción de duplicado
        }
    }

    public ItemDTO getItemByName(String itemName){
        return itemMapper.fromItem(itemRepository.findByName(itemName).orElseThrow(() -> new RuntimeException("Item not found")));
    }
}
