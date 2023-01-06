package dat3.delivery.dto;

import dat3.delivery.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    String name;
    double price;
    double weight;

    public static Product getProductEntity(ProductRequest pr) {
        return Product.builder()
                .name(pr.name)
                .price(pr.price)
                .weight(pr.weight)
                .build();
    }

    public ProductRequest(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.weight = product.getPrice();
    }
}
