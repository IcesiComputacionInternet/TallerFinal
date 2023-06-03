package co.edu.icesi.automoviles.model;

import java.util.UUID;
import java.util.List;

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

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private UUID itemId;
    private String description;
    private String name;
    private long price;
    private String imageUrl; 
    @ManyToOne
    @JoinColumn(name = "category_categoryId", nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "item_order",
        joinColumns = @JoinColumn(name = "item_itemId"),
        inverseJoinColumns = @JoinColumn(name = "purchaseOrder_purchaseOrderId")
    )
    private List<PurchaseOrder> purchaseOrder;
}
