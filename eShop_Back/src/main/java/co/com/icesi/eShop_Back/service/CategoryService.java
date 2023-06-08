package co.com.icesi.eShop_Back.service;

import co.com.icesi.eShop_Back.dto.request.RequestCategoryDTO;
import co.com.icesi.eShop_Back.dto.response.ResponseCategoryDTO;
import co.com.icesi.eShop_Back.mapper.CategoryMapper;
import co.com.icesi.eShop_Back.mapper.ItemMapper;
import co.com.icesi.eShop_Back.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ItemMapper itemMapper;

    //create crud
    public void create(RequestCategoryDTO requestCategoryDTO) {
        var category = categoryMapper.fromCategoryDTO(requestCategoryDTO);
        category.setCategoryId(UUID.randomUUID());
        categoryRepository.save(category);
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    public void update(RequestCategoryDTO requestCategoryDTO) {
        //TODO
    }

    public ResponseCategoryDTO get(UUID id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        var responseCategoryDTO = categoryMapper.fromCategory(category);
        var items = category.getItems().stream().map(itemMapper::fromItem).toList();
        responseCategoryDTO.setId(category.getCategoryId());
        responseCategoryDTO.setItems(items);
        return responseCategoryDTO;
    }

    public List<ResponseCategoryDTO> getAll() {
        var categories = categoryRepository.findAll();
        return categories.stream().map(aux -> {
            var items = aux.getItems().stream().map(itemMapper::fromItem).toList();
            var responseCategoryDTO = categoryMapper.fromCategory(aux);
            responseCategoryDTO.setId(aux.getCategoryId());
            responseCategoryDTO.setItems(items);
            return responseCategoryDTO;
        }).toList();
    }

}
