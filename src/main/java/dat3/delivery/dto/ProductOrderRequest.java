package dat3.delivery.dto;

import dat3.delivery.entity.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderRequest {
    int quantity;

    public static ProductOrder getProductOrderEntity(ProductOrderRequest por) {
        return ProductOrder.builder().quantity(por.quantity).build();
    }

    public ProductOrderRequest(ProductOrder productOrder) {
        this.quantity = productOrder.getQuantity();
    }
}
