package dat3.delivery.service;

import dat3.delivery.dto.VanResponse;
import dat3.delivery.entity.Delivery;
import dat3.delivery.entity.Van;
import dat3.delivery.repository.DeliveryRepository;
import dat3.delivery.repository.VanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VanService {
    VanRepository vanRepository;
    DeliveryRepository deliveryRepository;

    public VanService(VanRepository vanRepository, DeliveryRepository deliveryRepository) {
        this.vanRepository = vanRepository;
        this.deliveryRepository = deliveryRepository;
    }

    public List<VanResponse> getVans() {
        List<Van> vans = vanRepository.findAll();
        List<VanResponse> response = vans.stream().map(van -> new VanResponse(van)).toList();
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No vans found");
        }
        return response;
    }

    public VanResponse getVanById(int vanId) {
        Van van = vanRepository.findById(vanId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Van with this ID not found"));
        return new VanResponse(van);
    }

    public VanResponse addDeliveryToVan(int vanId, int deliveryId) {
        Van van = vanRepository.findById(vanId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No van with this id found"));
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No delivery with this id found"));
        if (delivery.getVan() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This delivery has already been assigned to a van.");
        }
        if (delivery.getTotalWeight() < van.getCapacity()) {
            van.getDeliveries().add(delivery);
            vanRepository.save(van);
            delivery.setVan(van);
            deliveryRepository.save(delivery);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This delivery is too heavy for the van");
        }
        return new VanResponse(van);
    }
}
