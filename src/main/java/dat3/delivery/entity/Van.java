package dat3.delivery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Van {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int vanId;
    String brand;
    String model;
    double capacity;

    @JsonIgnore
    @OneToMany
    List<Delivery> deliveries;

    public Van(String brand, String model, double capacity) {
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
    }
}
