package co.com.icesi.eShopBackEnd.model;

import co.com.icesi.eShopBackEnd.Enum.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrder {

    @Id
    private UUID orderId;
    private OrderState status;
    private Long total;

    @ManyToOne
    @JoinColumn(name = "customer_customer_id")
    private Customer customer;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "sales_order_item",
            joinColumns = { @JoinColumn(name = "order_order_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_item_id") }
    )
    private List<Item> items;

}
