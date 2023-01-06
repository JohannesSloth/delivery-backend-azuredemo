package dat3.delivery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.delivery.entity.Delivery;
import dat3.delivery.entity.ProductOrder;
import dat3.delivery.entity.Van;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryResponse {
    int deliveryId;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    LocalDate deliveryDate;
    String fromWarehouse;
    String destination;
    double totalPrice;
    double totalWeight;

    List<ProductOrder> productOrders;

    Van van;

    public DeliveryResponse(Delivery deliveryEntity) {
        this.deliveryId = deliveryEntity.getDeliveryId();
        this.deliveryDate = deliveryEntity.getDeliveryDate();
        this.fromWarehouse = deliveryEntity.getFromWarehouse();
        this.destination = deliveryEntity.getDestination();
        this.totalPrice = deliveryEntity.getTotalPrice();
        this.totalWeight = deliveryEntity.getTotalWeight();
        this.productOrders = deliveryEntity.getProductOrders();
        this.van = deliveryEntity.getVan();
    }

}
