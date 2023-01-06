package dat3.delivery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int deliveryId;
    LocalDate deliveryDate;
    String fromWarehouse;
    String destination;
    double totalPrice;
    double totalWeight;

    @OneToMany(mappedBy = "delivery")
    List<ProductOrder> productOrders = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    Van van;

    public Delivery(LocalDate deliveryDate, String fromWarehouse, String destination) {
        this.deliveryDate = deliveryDate;
        this.fromWarehouse = fromWarehouse;
        this.destination = destination;
    }
}
