package co.com.icesi.eShop_Back.service;

import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseItemDTO;
import co.com.icesi.eShop_Back.mapper.ItemMapper;
import co.com.icesi.eShop_Back.repository.CategoryRepository;
import co.com.icesi.eShop_Back.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;

    public void create(RequestItemDTO requestItem) {
        var category = categoryRepository.findByName(requestItem.category()).orElseThrow(() -> new RuntimeException("Category not found"));
        var item = itemMapper.fromItemDTO(requestItem);
        item.setCategory(category);
        item.setItemId(UUID.randomUUID());
        itemRepository.save(item);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    public void update(RequestItemDTO requestItem) {
        //TODO
    }

    public ResponseItemDTO get(UUID id) {
        var item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        var response = itemMapper.fromItem(item);
        response.setCategory(item.getCategory().getName());
        return response;
    }
}
