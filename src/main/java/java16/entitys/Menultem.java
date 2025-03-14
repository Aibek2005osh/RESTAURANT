package java16.entitys;

import jakarta.persistence.*;
import lombok. * ;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menultem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private BigDecimal price;

    private String desciption;

    private String isVegeterian;

}
