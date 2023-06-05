package co.edu.icesi.Eshop.service;

import co.edu.icesi.Eshop.dto.CategoryDTO;
import co.edu.icesi.Eshop.error.exception.DetailBuilder;
import co.edu.icesi.Eshop.error.exception.ErrorCode;
import co.edu.icesi.Eshop.mapper.CategoryMapper;
import co.edu.icesi.Eshop.model.Category;
import co.edu.icesi.Eshop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static co.edu.icesi.Eshop.error.util.EShopExceptionBuilder.createEShopException;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryDTO save(CategoryDTO categoryDTO){

        verifyCategoryName(categoryDTO.getName());
        Category category = categoryMapper.fromCategoryDTO(categoryDTO);

        category.setCategoryId(UUID.randomUUID());
        return categoryMapper.fromCategory(categoryRepository.save(category));
    }

    public List<CategoryDTO> getAllCategories(){
        adminAuthorizationOnly();
        return categoryRepository.findAll().stream().map(categoryMapper::fromCategory).toList();
    }

    private void verifyCategoryName(String categoryName){
        if (categoryRepository.findByName(categoryName).isPresent()){
            throw createEShopException(
                    "Duplicated category name",
                    HttpStatus.CONFLICT,
                    new DetailBuilder(ErrorCode.ERR_DUPLICATED, "Category with name", categoryName)
            ).get();
        }
    }

    private void adminAuthorizationOnly(){
        //Validar que el rol es un admin
        if (true){

        }
    }

    public CategoryDTO getCategoryByName(String categoryName){
        return categoryMapper.fromCategory(categoryRepository.findByName(categoryName).orElseThrow(createEShopException( "Category not found",
                HttpStatus.NOT_FOUND,
                new DetailBuilder(ErrorCode.ERR_404, "Category with name",categoryName )
        )));
    }
}
