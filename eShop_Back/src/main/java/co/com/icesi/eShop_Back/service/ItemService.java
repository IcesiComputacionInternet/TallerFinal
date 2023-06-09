package co.com.icesi.eShop_Back.service;

import co.com.icesi.eShop_Back.dto.request.RequestItemDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseItemDTO;
import co.com.icesi.eShop_Back.error.exception.CustomException;
import co.com.icesi.eShop_Back.mapper.ItemMapper;
import co.com.icesi.eShop_Back.repository.CategoryRepository;
import co.com.icesi.eShop_Back.repository.ItemRepository;
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

    public void create(RequestItemDTO requestItem) {
        var category = categoryRepository.findByName(requestItem.category()).orElseThrow(() -> new CustomException("Category not found"));
        var item = itemMapper.fromItemDTO(requestItem);
        item.setCategory(category);
        item.setItemId(UUID.randomUUID());
        itemRepository.save(item);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    public void update(RequestItemDTO requestItem, UUID id) {
        var category = categoryRepository.findByName(requestItem.category()).orElseThrow(() -> new CustomException("Category not found"));
        var newItem = itemMapper.fromItemDTO(requestItem);
        newItem.setItemId(id);
        newItem.setCategory(category);
        itemRepository.save(newItem);

    }

    public ResponseItemDTO get(UUID id) {
        var item = itemRepository.findById(id).orElseThrow(() -> new CustomException("Item not found"));
        var response = itemMapper.fromItem(item);
        response.setCategory(item.getCategory().getName());
        return response;
    }

    public List<ResponseItemDTO> getAll() {
        var items = itemRepository.findAll();
        return items.stream().map(item -> {
            var response = itemMapper.fromItem(item);
            response.setCategory(item.getCategory().getName());
            return response;
        }).toList();
    }
}
