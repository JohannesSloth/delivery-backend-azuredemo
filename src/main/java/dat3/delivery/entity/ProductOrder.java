package dat3.delivery.entity;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productOrderId;
    int quantity;

    @ManyToOne
    Product product;

    @JsonIgnore
    @ManyToOne
    Delivery delivery;

    public ProductOrder(int quantity, Product product, Delivery delivery) {
        this.quantity = quantity;
        this.product = product;
        this.delivery = delivery;
    }
}
