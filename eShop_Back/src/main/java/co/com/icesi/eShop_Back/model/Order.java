package co.com.icesi.eShop_Back.model;

import co.com.icesi.eShop_Back.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private UUID orderId;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_user_id")
    private User user;
    private Status status;
    private Long total;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_item",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private List<Item> items;
}
