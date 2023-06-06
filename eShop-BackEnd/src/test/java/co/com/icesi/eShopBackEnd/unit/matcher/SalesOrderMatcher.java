package co.com.icesi.eShopBackEnd.unit.matcher;

import co.com.icesi.eShopBackEnd.model.SalesOrder;
import org.mockito.ArgumentMatcher;

import java.util.Objects;

public class SalesOrderMatcher implements ArgumentMatcher<SalesOrder> {

    private final SalesOrder salesOrder;



    public SalesOrderMatcher(SalesOrder salesOrder){
        this.salesOrder = salesOrder;
    }




    @Override
    public boolean matches(SalesOrder salesOne) {
        return salesOne.getOrderId() != null &&
                Objects.equals(salesOrder.getStatus(), salesOne.getStatus()) &&
                Objects.equals(salesOrder.getTotal(), salesOne.getTotal()) ;
    }
}
