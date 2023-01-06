package dat3.delivery.service;

import dat3.delivery.dto.DeliveryRequest;
import dat3.delivery.dto.DeliveryResponse;
import dat3.delivery.entity.Delivery;
import dat3.delivery.repository.DeliveryRepository;
import dat3.delivery.repository.ProductOrderRepository;
import dat3.delivery.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class DeliveryServiceTest {

    public DeliveryService deliveryService;
    public ProductRepository productRepository;
    public ProductOrderRepository productOrderRepository;

    public static DeliveryRepository deliveryRepository;

    @BeforeAll
    public static void setupData(@Autowired DeliveryRepository delivery_Repository) {
        deliveryRepository = delivery_Repository;
        List<Delivery> deliveries = List.of(
                new Delivery(LocalDate.now(), "Ørestad Warehouse", "Amagerbrogade 11, 2300 København S"),
                new Delivery(LocalDate.now().plusDays(5), "Frederiksberg Warehouse", "Søndre Fasanvej 11, 2000 København"),
                new Delivery(LocalDate.now().plusDays(10), "Nørrebro Warehouse", "Nørrebrogade 23, 2200 København N")
        );
        deliveryRepository.saveAll(deliveries);
    }

    @BeforeEach
    public void setDeliveryService() {
        deliveryService = new DeliveryService(deliveryRepository, productRepository, productOrderRepository);
    }

    @Test
    void addDelivery() {
        Delivery delivery = new Delivery(LocalDate.now().plusDays(20), "Nørrebro Warehouse", "Guldbergsgade 29, 2200 København N");
        DeliveryRequest request = new DeliveryRequest(delivery);
        deliveryService.addDelivery(request);
        assertEquals(4, deliveryRepository.count());
    }

    @Test
    void getDeliveries() {
        List<DeliveryResponse> response = deliveryService.getDeliveries();
        assertEquals(3, response.size());
    }

    @Test
    void getDeliveryById() {
        DeliveryResponse response = deliveryService.getDelivery(1);
        assertEquals("Ørestad Warehouse", response.getFromWarehouse());
    }

    @Test
    void getDeliveryByWrongId() {
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> deliveryService.getDelivery(9999));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}