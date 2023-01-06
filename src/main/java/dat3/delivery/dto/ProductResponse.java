package dat3.delivery.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.delivery.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    int productId;
    String name;
    double price;
    double weight;

    public ProductResponse(Product productEntity) {
        this.productId = productEntity.getProductId();
        this.name = productEntity.getName();
        this.price = productEntity.getPrice();
        this.weight = productEntity.getWeight();
    }
}
