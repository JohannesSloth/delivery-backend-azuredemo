package dat3.delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.delivery.entity.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOrderResponse {
    int productOrderId;
    int quantity;

    public ProductOrderResponse(ProductOrder productOrderEntity) {
        this.productOrderId = productOrderEntity.getProductOrderId();
        this.quantity = productOrderEntity.getQuantity();
    }
}
