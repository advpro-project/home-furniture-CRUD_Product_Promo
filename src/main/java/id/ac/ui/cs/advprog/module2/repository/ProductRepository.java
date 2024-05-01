package id.ac.ui.cs.advprog.module2.repository;
import id.ac.ui.cs.advprog.module2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}

/* 

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        product.setId(UUID.randomUUID()); // Assign a UUID to the product
        products.add(product);
        return product;
    }
    public Product updateProduct(UUID productId, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                products.set(i, product);
                return product;
            }
        }
        return null;
    }
    public void deleteProduct(UUID productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }
    public Product getProductById(UUID productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
    public List<Product> getTop10PopularProducts() {
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }
}

*/