package co.com.icesi.backend.api;

import co.com.icesi.backend.dto.request.CategoryDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(CategoryAPI.BASE_CATEGORY_URL)
public interface CategoryAPI {
    String BASE_CATEGORY_URL = "/categories";
    @PostMapping("/create")
    CategoryDTO createCategory(@RequestBody @Valid CategoryDTO categoryDTO);
    @GetMapping("/{name}")
    CategoryDTO getCategory(@PathVariable("name") String categoryName);
    @GetMapping("/getCategories")
    List<CategoryDTO> getAllCategories();
}
