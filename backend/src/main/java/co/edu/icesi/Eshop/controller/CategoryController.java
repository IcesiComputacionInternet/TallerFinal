package co.edu.icesi.Eshop.controller;

import co.edu.icesi.Eshop.api.CategoryAPI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static co.edu.icesi.Eshop.api.CategoryAPI.BASE_CATEGORY_URL;

@RestController
@RequestMapping(BASE_CATEGORY_URL)
@AllArgsConstructor
public class CategoryController implements CategoryAPI {
}
