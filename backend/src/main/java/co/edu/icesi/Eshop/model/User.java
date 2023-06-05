package co.edu.icesi.Eshop.model;

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
public class User {
    @Id
    private UUID userId;

    private String firstName;

    private String lastName;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String phoneNumber;

    private String address;

    private LocalDate birthday;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<EShopOrder> orders;

    @ManyToOne(optional = false,cascade= CascadeType.ALL)
    @JoinColumn(name="role_role_id", nullable = false)
    private Role role;
}
