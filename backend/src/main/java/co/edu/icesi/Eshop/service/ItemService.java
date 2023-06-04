package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.ItemDTO;
import co.edu.icesi.Eshop.mapper.ItemMapper;
import co.edu.icesi.Eshop.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemDTO getItemByName(String itemName){
        return itemMapper.fromItem(itemRepository.findByName(itemName).orElseThrow(() -> new RuntimeException("Item not found")));
    }
}
