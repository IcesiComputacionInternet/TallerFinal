package co.com.icesi.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Cellphone {
    @Id
    private UUID cellphoneId;
    private String description;
    private String name;
    private Long price;
    private String imageUrl;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_category_id", nullable = false)
    private Category category;
}
