package co.com.icesi.backend.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime birthday;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_role_id", nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
