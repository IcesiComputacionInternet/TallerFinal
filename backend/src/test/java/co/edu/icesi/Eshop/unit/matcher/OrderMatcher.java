package co.edu.icesi.Eshop.unit.matcher;

import co.edu.icesi.Eshop.model.EShopOrder;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class OrderMatcher implements ArgumentMatcher<EShopOrder> {

    private EShopOrder orderLeft;

    public OrderMatcher(EShopOrder orderLeft){
        this.orderLeft=orderLeft;
    }
    @Override
    public boolean matches( EShopOrder orderRight) {
        return orderRight.getOrderId()!=null &&
                Objects.equals(orderRight.getStatus(),orderLeft.getStatus()) &&
                Objects.equals(orderRight.getTotal(), orderLeft.getTotal()) &&
                Objects.equals(orderRight.getItems().size(),orderLeft.getItems().size())
                ;
    }
}
