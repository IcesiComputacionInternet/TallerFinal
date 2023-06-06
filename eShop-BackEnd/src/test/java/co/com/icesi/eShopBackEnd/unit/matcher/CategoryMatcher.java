package co.com.icesi.eShopBackEnd.unit.matcher;

import co.com.icesi.eShopBackEnd.model.Category;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class CategoryMatcher implements ArgumentMatcher<Category> {

    private final Category category;


    public CategoryMatcher(Category category) {
        this.category = category;
    }


    @Override
    public boolean matches(Category categoryOne) {
        return categoryOne.getCategoryId() != null &&
                Objects.equals(category.getName(), categoryOne.getName()) &&
                Objects.equals(category.getDescription(), categoryOne.getDescription());
    }
}