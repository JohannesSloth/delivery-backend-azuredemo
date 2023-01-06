package dat3.delivery.api;

import dat3.delivery.dto.ProductRequest;
import dat3.delivery.dto.ProductResponse;
import dat3.delivery.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@CrossOrigin
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/findbyname/{productName}")
    public ProductResponse getProductByName(@PathVariable String productName) {
        return productService.getProductByName(productName);
    }

    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    @PutMapping("/{productId}")
    public ProductResponse editProduct(@PathVariable int productId, @RequestBody ProductRequest productBody) {
        return productService.editProduct(productId, productBody);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }
}
