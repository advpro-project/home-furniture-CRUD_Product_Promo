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
            existingProduct.setType(newProduct.getType());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setImageUrl(newProduct.getImageUrl());
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
        Optional<Product> productOptional = productRepository.findById(productId);

        // Check if the product exists
        if (productOptional.isPresent()) {
            // Return the product if found
            return productOptional.get();
        } else {
            // Throw an exception if the product is not found
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
    }

    @Override
    public List<Product> getTop10Products() {
        return productRepository.findFirst10ByOrderBySoldQuantityDesc();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
