package java16.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "stopLists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StopList {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String reason;
    private LocalDateTime date;



    @OneToOne
    private Menuitem menuitem;


    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }

}
