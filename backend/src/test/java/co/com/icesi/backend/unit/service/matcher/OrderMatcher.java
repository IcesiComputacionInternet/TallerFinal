package co.com.icesi.backend.unit.service.matcher;

import co.com.icesi.backend.model.ShopOrder;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class OrderMatcher implements ArgumentMatcher<ShopOrder> {
    private ShopOrder orderLeft;

    public OrderMatcher(ShopOrder orderLeft){
        this.orderLeft = orderLeft;
    }

    @Override
    public boolean matches(ShopOrder orderRight) {
        UserMatcher userMatcher = new UserMatcher(orderRight.getShopUser());
        return orderRight.getOrderId() != null &&
                userMatcher.matches(orderLeft.getShopUser()) &&
                Objects.equals(orderRight.getStatus().getStatus(), orderLeft.getStatus().getStatus()) &&
                Objects.equals(orderRight.getTotal(), orderLeft.getTotal());
    }
}
