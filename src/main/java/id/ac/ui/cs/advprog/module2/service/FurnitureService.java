package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.model.Furniture;

import java.util.UUID;
import java.util.List;

public interface ProductService {
    Furniture addProduct(Furniture furniture);
    Furniture updateProduct(UUID productId, Furniture furniture);
    void deleteProduct(UUID productId);
    Furniture getProductById(UUID productId);
    List<Furniture> getTop10Products();
    List<Furniture> getAllProducts();
}
