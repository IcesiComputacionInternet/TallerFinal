package co.com.icesi.Eshop.Unit.util;

import co.com.icesi.Eshop.model.Item;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class ItemMatcher implements ArgumentMatcher<Item> {
    @Override
    public boolean matches(Item itemM) {
        return false;
    }
}
