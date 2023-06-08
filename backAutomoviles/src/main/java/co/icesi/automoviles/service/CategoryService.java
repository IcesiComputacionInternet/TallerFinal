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
        checkIfTheNameIsAvailable(categoryCreateDTO.getName());
        Category category = categoryMapper.fromCategoryCreateDTOToCategory(categoryCreateDTO);
        category.setCategoryId(UUID.randomUUID());
        return categoryMapper.fromCategoryToCategoryShowDTO(categoryRepository.save(category));
    }

    public CategoryShowDTO updateCategory(String categoryId, CategoryCreateDTO categoryCreateDTO) {
        Category category = getCategory(UUID.fromString(categoryId));
        if (!category.getName().equals(categoryCreateDTO)){
            checkIfTheNameIsAvailable(categoryCreateDTO.getName());
        }
        Category updatedCategory = categoryMapper.fromCategoryCreateDTOToCategory(categoryCreateDTO);
        updatedCategory.setCategoryId(category.getCategoryId());
        return categoryMapper.fromCategoryToCategoryShowDTO(categoryRepository.save(category));
    }

    private Category getCategory(UUID categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(
                ShopExceptionBuilder.createShopException(
                        "category with id: "+categoryId+ " not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "category", "id ", categoryId)
                )
        );
    }

    private void checkIfTheNameIsAvailable(String categoryName){
        if(!categoryNameAvailable(categoryName)) {
            throw ShopExceptionBuilder.createShopException(
                    "There is already a category with the name: " + categoryName,
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_400, "name", "There is already a category with the name " + categoryName)
            ).get();
        }
    }

    private boolean categoryNameAvailable(String categoryName) {
        if (categoryRepository.findByName(categoryName).isPresent()) {
            return false;
        }
        return true;
    }
}
