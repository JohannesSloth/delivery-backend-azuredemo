package dat3.delivery.configuration;


import dat3.delivery.entity.Delivery;
import dat3.delivery.entity.Product;
import dat3.delivery.entity.Van;
import dat3.delivery.repository.DeliveryRepository;
import dat3.delivery.repository.ProductRepository;
import dat3.delivery.repository.VanRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    ProductRepository productRepository;
    DeliveryRepository deliveryRepository;
    VanRepository vanRepository;

    public SetupDevUsers(ProductRepository productRepository, DeliveryRepository deliveryRepository, VanRepository vanRepository) {
        this.productRepository = productRepository;
        this.deliveryRepository = deliveryRepository;
        this.vanRepository = vanRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        createDatabase();
    }

    private void createDatabase() {
        Product product1 = new Product("Apples", 15, 15);
        Product product2 = new Product("Sugar", 10, 10);
        Product product3 = new Product("Pepper", 20, 8);
        Product product4 = new Product("Soap", 25, 5);
        Product product5 = new Product("Wine", 40, 30);
        Product product6 = new Product("Beer", 25, 17);
        Product product7 = new Product("Sparkling Water", 5, 20);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);

        Delivery delivery1 = new Delivery(LocalDate.now(), "Ørestad Warehouse", "Amagerbrogade 11, 2300 København S");
        Delivery delivery2 = new Delivery(LocalDate.now().plusDays(5), "Frederiksberg Warehouse", "Søndre Fasanvej 11, 2000 København");
        Delivery delivery3 = new Delivery(LocalDate.now().plusDays(10), "Nørrebro Warehouse", "Nørrebrogade 23, 2200 København N");
        Delivery delivery4 = new Delivery(LocalDate.now().plusDays(15), "Centrum Warehouse", "Store Kongensgade 38, 1264 København");
        Delivery delivery5 = new Delivery(LocalDate.now().plusDays(20), "Østerbro Warehouse", "Koldingvej 17, 2100 København");
        deliveryRepository.save(delivery1);
        deliveryRepository.save(delivery2);
        deliveryRepository.save(delivery3);
        deliveryRepository.save(delivery4);
        deliveryRepository.save(delivery5);

        Van van1 = new Van("Ford", "Transit", 3000);
        Van van2 = new Van("MAN", "Exporter", 2800);
        Van van3 = new Van("Volvo", "Heavyweight", 3500);
        Van van4 = new Van("Mercedes", "Sprinter", 4000);
        vanRepository.save(van1);
        vanRepository.save(van2);
        vanRepository.save(van3);
        vanRepository.save(van4);
    }
}
