package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(UUID productId, Product newProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            // Update the existing product with new data
            existingProduct.setName(newProduct.getName());
            existingProduct.setCategory(newProduct.getCategory());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setImage(newProduct.getImage());
            existingProduct.setOriginalPrice(newProduct.getOriginalPrice());
            existingProduct.setDiscountedPrice(newProduct.getDiscountedPrice());

            // Save the updated product back to the database
            return productRepository.save(existingProduct);
        } else {
            // Handle case where product with given ID is not found
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
    }

    @Override
    public void deleteProduct(UUID productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
        } else {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
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
