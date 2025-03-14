package java16.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

}

