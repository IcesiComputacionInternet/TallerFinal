package co.com.icesi.eShopBackEnd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDate birthday;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_role_id")
    private Role role;

    @OneToMany(mappedBy = "customer")
    private List<SalesOrder> salesOrders;
}
