package co.edu.icesi.automoviles.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    @Id
    private UUID purchaseOrderId;

    @ManyToOne
    @JoinColumn(name = "customer_customerId", nullable = false)
    private Customer customer;

    private String status;
    private long total;

    @ManyToMany
    @JoinTable(
        name = "item_order",
        joinColumns = @JoinColumn(name = "purchaseOrder_purchaseOrderId"),
        inverseJoinColumns = @JoinColumn(name = "item_itemId")
    )
    private List<Item> items;
    
}
