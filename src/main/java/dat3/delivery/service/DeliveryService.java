package dat3.delivery.service;

import dat3.delivery.dto.DeliveryRequest;
import dat3.delivery.dto.DeliveryResponse;
import dat3.delivery.entity.Delivery;
import dat3.delivery.entity.Product;
import dat3.delivery.entity.ProductOrder;
import dat3.delivery.repository.DeliveryRepository;
import dat3.delivery.repository.ProductOrderRepository;
import dat3.delivery.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeliveryService {
    DeliveryRepository deliveryRepository;
    ProductRepository productRepository;
    ProductOrderRepository productOrderRepository;


    public DeliveryService(DeliveryRepository deliveryRepository, ProductRepository productRepository, ProductOrderRepository productOrderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.productRepository = productRepository;
        this.productOrderRepository = productOrderRepository;
    }

    public List<DeliveryResponse> getDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        List<DeliveryResponse> response = deliveries.stream().map(delivery -> new DeliveryResponse(delivery)).toList();
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No deliveries found");
        }
        return response;
    }

    public DeliveryResponse getDelivery(int deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No delivery with this id found"));
        return new DeliveryResponse(delivery);
    }

    public DeliveryResponse addDelivery(DeliveryRequest deliveryRequest) {
        Delivery newDelivery = DeliveryRequest.getDeliveryEntity(deliveryRequest);
        newDelivery = deliveryRepository.save(newDelivery);
        return new DeliveryResponse(newDelivery);
    }

    public DeliveryResponse addProductOrderToDelivery(int deliveryId, int productId, int quantity) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No delivery with this id found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No product with this id found"));

        ProductOrder productOrder = new ProductOrder();
        productOrder.setQuantity(quantity);
        productOrder.setDelivery(delivery);
        productOrder.setProduct(product);
        productOrderRepository.save(productOrder);

        delivery.getProductOrders().add(productOrder);

        double totalPrice = delivery.getTotalPrice() + productOrder.getQuantity() * product.getPrice();
        double totalWeight = delivery.getTotalWeight() + productOrder.getQuantity() * product.getWeight();

        delivery.setTotalPrice(totalPrice);
        delivery.setTotalWeight(totalWeight);

        deliveryRepository.save(delivery);

        return new DeliveryResponse(delivery);
    }

}
