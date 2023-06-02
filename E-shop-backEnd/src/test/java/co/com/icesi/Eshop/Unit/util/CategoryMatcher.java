package co.com.icesi.Eshop.Unit.util;

import co.com.icesi.Eshop.model.Category;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class CategoryMatcher implements ArgumentMatcher<Category> {
    @Override
    public boolean matches(Category category) {
        return false;
    }
}
