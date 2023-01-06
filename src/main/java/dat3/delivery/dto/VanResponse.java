package dat3.delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.delivery.entity.Delivery;
import dat3.delivery.entity.Van;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VanResponse {
    int vanId;
    String brand;
    String model;
    double capacity;
    List<Delivery> deliveries;

    public VanResponse(Van vanEntity) {
        this.vanId = vanEntity.getVanId();
        this.brand = vanEntity.getBrand();
        this.model = vanEntity.getModel();
        this.capacity = vanEntity.getCapacity();
        this.deliveries = vanEntity.getDeliveries();
    }
}
