package java16.entitys;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "subcategories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
