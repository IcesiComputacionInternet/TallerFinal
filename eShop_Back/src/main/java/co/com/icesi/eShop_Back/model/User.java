package co.com.icesi.eShop_Back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "`user`")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDateTime brithDay;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "role_role_id")
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
