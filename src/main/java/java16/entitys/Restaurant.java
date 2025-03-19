package java16.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Entity
@Table(name ="restaurants" )
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private String restType;

    private int numberOfEmployees;

    private double service;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menuitem> menuItems;




}
