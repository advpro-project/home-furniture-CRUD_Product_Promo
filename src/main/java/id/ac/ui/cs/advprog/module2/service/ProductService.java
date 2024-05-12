package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.model.Product;

import java.util.concurrent.CompletableFuture;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    CompletableFuture<Product> getProductById(Long productId);
    List<Product> getTop10Products();
    CompletableFuture<List<Product>> getAllProducts();
}
