package co.icesi.automoviles.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.CategoryShowDTO;
import co.icesi.automoviles.error.exception.DetailBuilder;
import co.icesi.automoviles.error.exception.ErrorCode;
import co.icesi.automoviles.error.util.ShopExceptionBuilder;
import co.icesi.automoviles.mapper.CategoryMapper;
import co.icesi.automoviles.model.Category;
import co.icesi.automoviles.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryShowDTO registerCategory(CategoryCreateDTO categoryCreateDTO) {
        if(!categoryNameAvailable(categoryCreateDTO.getName())) {
            throw ShopExceptionBuilder.createShopException(
                "There is already a category with the name: " + categoryCreateDTO.getName(),
                HttpStatus.BAD_REQUEST,
                new DetailBuilder(ErrorCode.ERR_400, "name", "There is already a category with the name " + categoryCreateDTO.getName())
            ).get();
        }

        Category category = categoryMapper.fromCategoryCreateDTOToCategory(categoryCreateDTO);
        category.setCategoryId(UUID.randomUUID());
        return categoryMapper.fromCategoryToCategoryShowDTO(categoryRepository.save(category));
    }

    private boolean categoryNameAvailable(String categoryName) {
        if (categoryRepository.findByName(categoryName).isPresent()) {
            return false;
        }
        return true;
    }
}
