package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    @Override
    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public Product updateProduct(UUID productId, Product product) {
        return productRepository.updateProduct(productId, product);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteProduct(productId);
    }

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public List<Product> getTop10PopularProducts() {
        return productRepository.getTop10PopularProducts();
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts(); 
    }
}
