package dat3.delivery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.delivery.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    LocalDate deliveryDate;
    String fromWarehouse;
    String destination;

    public static Delivery getDeliveryEntity(DeliveryRequest dr) {
        return Delivery.builder()
                .deliveryDate(dr.deliveryDate)
                .fromWarehouse(dr.fromWarehouse)
                .destination(dr.destination)
                .build();
    }

    public DeliveryRequest(Delivery delivery) {
        this.deliveryDate = delivery.getDeliveryDate();
        this.fromWarehouse = delivery.getFromWarehouse();
        this.destination = delivery.getDestination();
    }
}
