package co.com.icesi.Eshop.api;

import co.com.icesi.Eshop.dto.request.CategoryDTO;
import co.com.icesi.Eshop.dto.response.CategoryResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(CategoryApi.BASE_URL)
public interface CategoryApi {
    String BASE_URL = "api/categories";

    @PostMapping("/create")
    CategoryResponseDTO createCategory(CategoryDTO categoryResponseDTO);

    @PostMapping("/update")
    CategoryResponseDTO updateCategory(CategoryDTO categoryResponseDTO);

    @PostMapping("/delete")
    CategoryResponseDTO deleteCategory(CategoryDTO categoryResponseDTO);

    @GetMapping
    List<CategoryResponseDTO> getAllCategories();
}
