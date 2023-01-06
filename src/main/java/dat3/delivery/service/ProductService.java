package dat3.delivery.service;

import dat3.delivery.dto.ProductRequest;
import dat3.delivery.dto.ProductResponse;
import dat3.delivery.entity.Product;
import dat3.delivery.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> response = products.stream().map(product -> new ProductResponse(product)).toList();
        return response;
    }

    public ProductResponse getProductById(int productId) {
        Product found = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with this ID not found"));
        return new ProductResponse(found);
    }

    public ProductResponse getProductByName(String productName) {
        Product found = productRepository.findProductByName(productName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with this name not found"));
        return new ProductResponse(found);
    }

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product newProduct = ProductRequest.getProductEntity(productRequest);
        newProduct = productRepository.save(newProduct);
        return new ProductResponse(newProduct);
    }

    public ProductResponse editProduct(int productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with this ID doesn't exist"));
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setWeight(productRequest.getWeight());
        productRepository.save(product);
        return new ProductResponse(product);
    }

    public void deleteProduct(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No product with this ID exists"));
        productRepository.delete(product);
    }
}
