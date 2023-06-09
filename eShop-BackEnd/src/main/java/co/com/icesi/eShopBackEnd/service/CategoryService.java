package co.com.icesi.eShopBackEnd.service;

import co.com.icesi.eShopBackEnd.dto.AssignCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.CreateCategoryDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseDTO;
import co.com.icesi.eShopBackEnd.dto.response.ResponseItemDTO;
import co.com.icesi.eShopBackEnd.error.enums.ErrorCode;
import co.com.icesi.eShopBackEnd.error.util.ArgumentsExceptionBuilder;
import co.com.icesi.eShopBackEnd.error.util.DetailBuilder;
import co.com.icesi.eShopBackEnd.mapper.CategoryMapper;
import co.com.icesi.eShopBackEnd.mapper.ItemMapper;
import co.com.icesi.eShopBackEnd.model.Category;
import co.com.icesi.eShopBackEnd.model.Item;
import co.com.icesi.eShopBackEnd.repository.CategoryRepository;
import co.com.icesi.eShopBackEnd.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final CategoryMapper categoryMapper;
    private final ItemMapper itemMapper;

    public CreateCategoryDTO save(CreateCategoryDTO categoryDTO){
        boolean exists = categoryRepository.categoryExists(categoryDTO.name());
        if (exists){
            throw ArgumentsExceptionBuilder.createArgumentsException(
                    "Existing data",
                    HttpStatus.BAD_REQUEST,
                    new DetailBuilder(ErrorCode.ERR_406,"Category name")
            );
            //throw new ArgumentsException("Category name already exist");
        }
        Category category = categoryMapper.fromCreateCategoryDTO(categoryDTO);
        category.setCategoryId(UUID.randomUUID());
        return categoryMapper.fromCategory(categoryRepository.save(category));
    }

    @Transactional
    public ResponseItemDTO assignCategory(AssignCategoryDTO assignCategoryDTO){
        Category category = validateExistingCategory(assignCategoryDTO.category());
        Item item = validateExistingItem(assignCategoryDTO.itemName());

        item.setCategory(category);
        itemRepository.updateCategory(item.getName(),category);
        return itemMapper.fromItemToResponseItemDTO(item);
    }

    private Category validateExistingCategory(String name){
        return categoryRepository.returnCategory(name).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Category not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"Category")
                )

        );
    }

    private Item validateExistingItem(String name){
        return itemRepository.returnItem(name).orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Item not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"Item")
                )

        );
    }

    public List<ResponseItemDTO> getItemsByCategory(String categoryName){
        Category category = validateExistingCategory(categoryName);
        List<Item> items = category.getItems();
        return items.stream().map(itemMapper::fromItemToResponseItemDTO).toList();

    }

    public List<CreateCategoryDTO> getAllCategories(){
        List<Category> list = categoryRepository.findAll();
        return list.stream().map(categoryMapper::fromCategory).toList();
    }

    @Transactional
    public ResponseDTO deleteCategory(CreateCategoryDTO categoryDTO){
        Category category = validateExistingCategory(categoryDTO.name());

        setNoneCategory(category.getName());

        categoryRepository.delete(category);

        return ResponseDTO.builder()
                .message("Category deleted")
                .build();

    }

    @Transactional
    public void setNoneCategory(String categoryName){
        Category none = categoryRepository.returnCategory("NONE").orElseThrow(
                ArgumentsExceptionBuilder.createArgumentsExceptionSup(
                        "Category not found",
                        HttpStatus.NOT_FOUND,
                        new DetailBuilder(ErrorCode.ERR_NOT_FOUND,"Category")
                )
        );

        List<Item> items = getItemsByCategory(categoryName)
                .stream()
                .map(itemMapper::fromResponseItemDTOToItem)
                .toList();

        items.forEach(item ->{
            item.setCategory(none);
            itemRepository.updateCategory(item.getName(), none);
        });

    }

}
