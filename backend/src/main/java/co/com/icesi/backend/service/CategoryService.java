package co.com.icesi.backend.service;

import co.com.icesi.backend.Enum.UserRole;
import co.com.icesi.backend.dto.request.CategoryDTO;
import co.com.icesi.backend.error.util.CellphoneShopExceptionBuilder;
import co.com.icesi.backend.mapper.CategoryMapper;
import co.com.icesi.backend.model.Category;
import co.com.icesi.backend.repository.CategoryRepository;
import co.com.icesi.backend.security.CellphoneSecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CellphoneShopExceptionBuilder exceptionBuilder;

    public CategoryDTO save(CategoryDTO categoryDTO){
        checkPermissions();

        if(categoryRepository.isNameInUse(categoryDTO.getName())){
            throw exceptionBuilder.duplicatedValueException(
                    "Another role already has this name.", categoryDTO.getName());
        }

        Category category = categoryMapper.fromCategoryDTO(categoryDTO);
        category.setCategoryId(UUID.randomUUID());
        categoryRepository.save(category);
        return categoryMapper.fromCategoryToCategoryDTO(category);
    }

    public CategoryDTO getCategory(String categoryName){
        checkPermissions();
        return categoryMapper.fromCategoryToCategoryDTO(
                categoryRepository.findByName(categoryName)
                        .orElseThrow(() -> exceptionBuilder.notFoundException(
                        "The role with the specified name does not exists.", categoryName))
        );
    }

    public void checkPermissions() {
        if(!CellphoneSecurityContext.getCurrentUserRole().equals(UserRole.ADMIN)){
            throw exceptionBuilder.noPermissionException(
                    "Only an ADMIN user can create new roles and visualize them."
            );
        }
    }

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(categoryMapper::fromCategoryToCategoryDTO).collect(Collectors.toList());
    }
}
