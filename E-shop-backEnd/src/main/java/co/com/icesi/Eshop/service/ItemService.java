package co.com.icesi.Eshop.service;

import co.com.icesi.Eshop.dto.request.ItemDTO;
import co.com.icesi.Eshop.dto.response.ItemResponseDTO;
import co.com.icesi.Eshop.mapper.ItemMapper;
import co.com.icesi.Eshop.model.Item;
import co.com.icesi.Eshop.repository.CategoryRepository;
import co.com.icesi.Eshop.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;
    public ItemResponseDTO createItem(ItemDTO itemDTO) {
        /*
        Item item = itemMapper.toItem(itemDTO);
        item.setCategory(categoryRepository.findById(UUID.fromString(itemDTO.getCategoryId())).orElseThrow(() -> new RuntimeException("Category not found")));
        item.setOrder(null); //TODO: set order correctly
        return itemMapper.toItemResponseDTO(itemRepository.save(item));
         */
        return null;
    }

    public List<ItemResponseDTO> getAllItems() {
        return itemRepository.findAll().stream().map(itemMapper::toItemResponseDTO).collect(Collectors.toList());
    }
}
