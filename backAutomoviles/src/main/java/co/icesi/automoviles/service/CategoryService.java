package co.icesi.automoviles.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.icesi.automoviles.dto.ItemShowDTOForCategory;
import co.icesi.automoviles.mapper.ItemMapper;
import co.icesi.automoviles.model.Item;
import co.icesi.automoviles.service.utils.SortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ItemMapper itemMapper;

    public CategoryShowDTO registerCategory(CategoryCreateDTO categoryCreateDTO) {
        checkIfTheNameIsAvailable(categoryCreateDTO.getName());
        Category category = categoryMapper.fromCategoryCreateDTOToCategory(categoryCreateDTO);
        category.setCategoryId(UUID.randomUUID());
        CategoryShowDTO categoryShowDTO = categoryMapper.fromCategoryToCategoryShowDTO(categoryRepository.save(category));
        categoryShowDTO.setItems(new ArrayList<>());
        return categoryShowDTO;

    }

    public CategoryShowDTO updateCategory(String categoryId, CategoryCreateDTO categoryCreateDTO) {
        Category category = getCategory(UUID.fromString(categoryId));
        if (!category.getName().equals(categoryCreateDTO)){
            checkIfTheNameIsAvailable(categoryCreateDTO.getName());
        }
        List<Item> itemList = category.getItems();
        Category updatedCategory = categoryMapper.fromCategoryCreateDTOToCategory(categoryCreateDTO);
        updatedCategory.setCategoryId(category.getCategoryId());
        CategoryShowDTO categoryShowDTO = categoryMapper.fromCategoryToCategoryShowDTO(categoryRepository.save(updatedCategory));
        List<ItemShowDTOForCategory> itemShowDTOForCategoryList = itemList.stream().map(itemMapper::fromItemToItemItemShowDTOForCategory).toList();
        categoryShowDTO.setItems(itemShowDTOForCategoryList);
        return categoryShowDTO;
    }

    public Page<CategoryShowDTO> getAllCategories(int page, int perPage, String sortBy, String sortDir){
        Pageable pageable = SortUtil.sort(page, perPage, sortBy, sortDir);
        Page<Category> categories = categoryRepository.getAllCategories(pageable);
        return categories.map(categoryMapper::fromCategoryToCategoryShowDTO);
    }

    private Category getCategory(UUID categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(
                ShopExceptionBuilder.createShopException(
                        "category with id: "+categoryId+ " not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_404, "category", "id", categoryId)
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
