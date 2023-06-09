package com.example.eshopbackend.service;

import com.example.eshopbackend.dto.ItemDTO;
import com.example.eshopbackend.mapper.ItemMapper;
import com.example.eshopbackend.model.Category;
import com.example.eshopbackend.model.Item;
import com.example.eshopbackend.repository.CategoryRepository;
import com.example.eshopbackend.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;

    public ItemDTO addItem(ItemDTO newItem){
        validateItemName(newItem.getName());
        Category category = validateItemCategory(newItem.getCategory().getName());
        validateItemPrice(newItem.getPrice());
        Item item = itemMapper.fromItemDTO(newItem);
        item.setItemId(UUID.randomUUID());
        item.setCategory(category);

        return itemMapper.fromItem(itemRepository.save(item));
    }

    public List<ItemDTO> getAllItems(){
        return itemRepository.findAll().stream().map(itemMapper::fromItem).toList();
    }

    public ItemDTO getItemById(UUID itemId){
        return itemMapper.fromItem(itemRepository.findById(itemId).orElseThrow(
            () -> new RuntimeException("El item con el id " + itemId + " no existe.")
        ));
    }

    private Category validateItemCategory(String category){
        if(categoryRepository.findByName(category).isEmpty()){
            throw new RuntimeException("La categoría " + category + " no existe.");
        }else{
            return categoryRepository.findByName(category).get();
        }
    }

    private void validateItemName(String name){
        if(itemRepository.findByName(name).isPresent()){
            throw new RuntimeException("El item con el nombre " + name + " ya existe.");
        }
    }

    private void validateItemPrice(long price){
        if(price <= 0){
            throw new RuntimeException("El precio no puede ser negativo o cero.");
        }
    }

}
