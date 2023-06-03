package co.edu.icesi.automoviles.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

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
    private UUID orderId;
    //private User user;
    private String status;
    private long total;
    //private List<Item> items;
    
}
