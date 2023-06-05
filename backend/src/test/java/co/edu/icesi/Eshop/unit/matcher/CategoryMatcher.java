package co.edu.icesi.Eshop.unit.matcher;

import co.edu.icesi.Eshop.model.Category;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class CategoryMatcher implements ArgumentMatcher<Category> {

    private Category categoryLeft;

    public CategoryMatcher(Category categoryLeft){
        this.categoryLeft = categoryLeft;
    }

    @Override
    public boolean matches(Category categoryRight) {
        return categoryRight.getCategoryId() != null &&
                Objects.equals(categoryRight.getName(), categoryLeft.getName()) &&
                Objects.equals(categoryRight.getDescription(), categoryLeft.getDescription());
    }
}
