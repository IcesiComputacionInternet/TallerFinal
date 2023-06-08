package co.com.icesi.backend.model;

import co.com.icesi.backend.Enum.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ShopOrder {
    @Id
    private UUID orderId;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_user_user_id", nullable = false)
    private ShopUser shopUser;
    private OrderStatus status;
    private long total;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "shopOrder_order_id"),
            inverseJoinColumns = @JoinColumn(name = "cellphone_cellphone_id"))
    private List<Cellphone> items;

    @ElementCollection
    @Column(name = "quantities")
    private Set<Integer> quantities;
}
