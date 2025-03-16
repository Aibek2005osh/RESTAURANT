package java16.entitys;

import jakarta.persistence.*;
import java16.enums.VegetarianStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Menuitem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private BigDecimal price;

    private String description;

    private VegetarianStatus isVegeterian;

    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;

    @OneToOne(mappedBy = "menuitem", cascade = CascadeType.ALL)
    private StopList stopList;

    @ManyToMany(mappedBy = "menuitems")
    private List<Cheque> cheques;

    @ManyToOne
    private Subcategory subcategory;


}
