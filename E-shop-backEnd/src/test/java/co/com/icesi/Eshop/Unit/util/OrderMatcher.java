package co.com.icesi.Eshop.Unit.util;

import co.com.icesi.Eshop.model.OrderStore;
import lombok.AllArgsConstructor;
import org.mockito.ArgumentMatcher;

@AllArgsConstructor
public class OrderMatcher implements ArgumentMatcher<OrderStore> {
    @Override
    public boolean matches(OrderStore orderStore) {
        return false;
    }
}
