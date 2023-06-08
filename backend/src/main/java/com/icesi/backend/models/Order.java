package com.icesi.backend.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Data
@Table(name = "orders")
public class Order {
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    @ManyToOne
    private ShopUser shopUser;
    private String status;
    private Long total;
    /*
    @OneToMany()
    private List<Item> itemList;
     */
}
