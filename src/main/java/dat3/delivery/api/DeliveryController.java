package dat3.delivery.api;

import dat3.delivery.dto.DeliveryRequest;
import dat3.delivery.dto.DeliveryResponse;
import dat3.delivery.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/deliveries")
@CrossOrigin
public class DeliveryController {
    DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<DeliveryResponse> getDeliveries() {
        return deliveryService.getDeliveries();
    }

    @GetMapping("/{deliveryId}")
    public DeliveryResponse getDelivery(@PathVariable int deliveryId) {
        return deliveryService.getDelivery(deliveryId);
    }

    @PostMapping
    public DeliveryResponse addDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        return deliveryService.addDelivery(deliveryRequest);
    }

    @PostMapping("/addproductorder/{deliveryId}/{productId}/{quantity}")
    public DeliveryResponse addProductOrderToDelivery(@PathVariable int deliveryId, @PathVariable int productId, @PathVariable int quantity) {
        return deliveryService.addProductOrderToDelivery(deliveryId, productId, quantity);
    }
}
