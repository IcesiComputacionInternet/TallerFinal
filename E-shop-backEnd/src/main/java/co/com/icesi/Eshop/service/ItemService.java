package co.com.icesi.Eshop.service;

import co.com.icesi.Eshop.dto.request.ItemDTO;
import co.com.icesi.Eshop.dto.response.ItemResponseDTO;
import co.com.icesi.Eshop.mapper.ItemMapper;
import co.com.icesi.Eshop.model.Item;
import co.com.icesi.Eshop.repository.CategoryRepository;
import co.com.icesi.Eshop.repository.ItemRepository;
import co.com.icesi.Eshop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;
    public ItemResponseDTO createItem(ItemDTO itemDTO) {
        Item item = itemMapper.toItem(itemDTO);
        item.setCategory(categoryRepository.findById(UUID.fromString(itemDTO.getCategoryId())).orElseThrow(() -> new RuntimeException("Category not found")));
        item.setItemId(UUID.randomUUID());
        return itemMapper.toItemResponseDTO(itemRepository.save(item));
    }

    public ItemResponseDTO updateItem(ItemDTO itemDTO) {
        Item item = itemRepository.findByName(itemDTO.getName()).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setCategory(categoryRepository.findById(UUID.fromString(itemDTO.getCategoryId())).orElseThrow(() -> new RuntimeException("Category not found")));
        item.setDescription(itemDTO.getDescription());
        item.setImageUrl(itemDTO.getImageUrl());
        item.setPrice(itemDTO.getPrice());
        item.setName(itemDTO.getName());
        return itemMapper.toItemResponseDTO(itemRepository.save(item));
    }

    public String deleteItem(String itemName) {
        Item item = itemRepository.findByName(itemName).orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(item);
        return "Item deleted";
    }

    public ItemResponseDTO findItem(String itemName) {
        Item item = itemRepository.findByName(itemName).orElseThrow(() -> new RuntimeException("Item not found"));
        return itemMapper.toItemResponseDTO(item);
    }

    public List<ItemResponseDTO> getAllItems() {
        return itemRepository.findAll().stream().map(itemMapper::toItemResponseDTO).collect(Collectors.toList());
    }
}
