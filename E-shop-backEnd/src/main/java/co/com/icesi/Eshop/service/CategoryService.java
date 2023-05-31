package co.com.icesi.Eshop.service;

import co.com.icesi.Eshop.dto.request.CategoryDTO;
import co.com.icesi.Eshop.dto.response.CategoryResponseDTO;
import co.com.icesi.Eshop.mapper.CategoryMapper;
import co.com.icesi.Eshop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;

    private final CategoryRepository categoryRepository;
    public CategoryResponseDTO createCategory(CategoryDTO categoryDTO) {
        return categoryMapper.toCategoryResponseDTO(categoryRepository.save(categoryMapper.toCategory(categoryDTO)));
    }
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponseDTO).collect(Collectors.toList());
    }
}
