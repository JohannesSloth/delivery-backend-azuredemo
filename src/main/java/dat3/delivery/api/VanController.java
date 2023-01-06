package dat3.delivery.api;

import dat3.delivery.dto.VanResponse;
import dat3.delivery.service.VanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vans")
@CrossOrigin
public class VanController {
    VanService vanService;

    public VanController(VanService vanService) {
        this.vanService = vanService;
    }

    @GetMapping
    public List<VanResponse> getVans() {
        return vanService.getVans();
    }

    @GetMapping("/{vanId}")
    public VanResponse getVanById(@PathVariable int vanId) {
        return vanService.getVanById(vanId);
    }

    @PostMapping("/{vanId}/{deliveryId}")
    public VanResponse addDeliveryToVan(@PathVariable int vanId, @PathVariable int deliveryId) {
        return vanService.addDeliveryToVan(vanId, deliveryId);
    }
}
