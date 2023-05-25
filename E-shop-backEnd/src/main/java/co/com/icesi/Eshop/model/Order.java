package co.com.icesi.Eshop.model;

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
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private UUID orderId;

    @ManyToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "user_user_id")
    private User user;

    private String status;

    private Long total;

    @OneToMany(mappedBy = "order", cascade = javax.persistence.CascadeType.ALL)
    private List<Item> items;
}
