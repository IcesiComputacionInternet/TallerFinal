package com.example.eshopbackend.model;

import com.example.eshopbackend.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
public class Order {
    @Id
    private UUID orderId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="user_user_id", nullable=false)
    private User user;

    private OrderStatus status;

    private long total;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;


}
