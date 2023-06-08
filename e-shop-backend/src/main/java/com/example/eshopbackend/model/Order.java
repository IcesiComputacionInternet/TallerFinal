package com.example.eshopbackend.model;

import com.example.eshopbackend.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
public class Order {
    @Id
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;

    private OrderStatus status;

    private long total;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "itemId"))
    private List<Item> items;


}
