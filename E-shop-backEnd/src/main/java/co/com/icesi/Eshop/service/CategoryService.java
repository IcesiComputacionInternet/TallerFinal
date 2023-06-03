package co.com.icesi.Eshop.service;

import co.com.icesi.Eshop.dto.request.CategoryDTO;
import co.com.icesi.Eshop.dto.response.CategoryResponseDTO;
import co.com.icesi.Eshop.mapper.CategoryMapper;
import co.com.icesi.Eshop.model.Category;
import co.com.icesi.Eshop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;
    public CategoryResponseDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toCategory(categoryDTO);
        category.setCategoryId(UUID.randomUUID());
        return categoryMapper.toCategoryResponseDTO(categoryRepository.save(category));
    }
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponseDTO).collect(Collectors.toList());
    }
}
