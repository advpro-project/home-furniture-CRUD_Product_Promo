package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.model.Product;

import java.util.UUID;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(UUID productId, Product product);
    void deleteProduct(UUID productId);
    Product getProductById(UUID productId);
    List<Product> getTop10PopularProducts();
}
