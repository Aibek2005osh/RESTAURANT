package java16.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cheque {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double priceAverage;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;


    @ManyToMany
    @JoinTable(
            name = "cheque_menuitem",
            joinColumns = @JoinColumn(name = "cheque_id"),
            inverseJoinColumns = @JoinColumn(name = "menuitem_id")
    )
    private List<Menuitem> menuitems;

}

