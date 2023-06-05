package co.edu.icesi.Eshop.model;

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
public class EShopOrder {

    @Id
    private UUID orderId;

    private String status;

    private long total;

    @ManyToOne(optional = false,cascade= CascadeType.ALL)
    @JoinColumn(name="eshop_user_user_id", nullable = false)
    private EShopUser eShopUser;

    @OneToMany(mappedBy = "order")
    private List<Item> items;



}
