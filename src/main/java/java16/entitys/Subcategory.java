package java16.entitys;

import jakarta.persistence.*;

import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    private List<Menuitem> menuItems;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
