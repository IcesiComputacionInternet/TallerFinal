package co.edu.icesi.Eshop.api;

import co.edu.icesi.Eshop.dto.CategoryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface CategoryAPI {

    String BASE_CATEGORY_URL="/categories";

    @GetMapping("/{categoryName}")
    CategoryDTO getCategory(@PathVariable String categoryName);

    @GetMapping
    List<CategoryDTO> getAllCategories();

    @PostMapping
    CategoryDTO addCategory(@Valid @RequestBody CategoryDTO categoryDTO);
}
