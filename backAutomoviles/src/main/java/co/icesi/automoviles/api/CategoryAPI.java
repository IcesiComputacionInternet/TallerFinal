package co.icesi.automoviles.api;

import javax.validation.Valid;

import co.icesi.automoviles.dto.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.icesi.automoviles.dto.CategoryCreateDTO;
import co.icesi.automoviles.dto.CategoryShowDTO;

@RequestMapping("/categories")
public interface CategoryAPI {
    
    public static final String ROOT_PATH = "/categories";

    @GetMapping
    public ResponseEntity<PageResponse<CategoryShowDTO>> getCategories(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "per_page", defaultValue = "10") Integer perPage,
            @RequestParam(name = "sort", defaultValue = "name") String sortBy,
            @RequestParam(name = "sort_dir", defaultValue = "asc") String sortDirection);

    @PostMapping
    public CategoryShowDTO registerCategory(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO);

    @PatchMapping("{categoryId}")
    public CategoryShowDTO updateCategory(@PathVariable("categoryId") String categoryId, CategoryCreateDTO categoryCreateDTO);

}
