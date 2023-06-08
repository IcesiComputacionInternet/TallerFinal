package co.com.icesi.backend.model;

import co.com.icesi.backend.Enum.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    @Id
    private UUID orderId;
    @ManyToOne
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;
    private OrderStatus status;
    private long total;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_order_id"),
            inverseJoinColumns = @JoinColumn(name = "cellphone_cellphone_id"))
    private List<Cellphone> items;

    @ElementCollection
    @Column(name = "quantities")
    private Set<Integer> quantities;
}
