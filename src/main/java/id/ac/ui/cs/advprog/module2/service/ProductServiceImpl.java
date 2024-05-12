package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product newProduct) {
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
    public void deleteProduct(Long productId) {
        CompletableFuture<Product> productFuture = getProductById(productId);

        productFuture.thenAccept(product -> {
            productRepository.delete(product);
        }).join();
    }

    @Override
    @Async
    public CompletableFuture<Product> getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        
        if (productOptional.isPresent()) {
            return CompletableFuture.completedFuture(productOptional.get());
        } else {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
    }

    @Override
    public List<Product> getTop10Products() {
        return productRepository.findFirst10ByOrderBySoldQuantityDesc();
    }

    @Override
    public CompletableFuture<List<Product>> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return CompletableFuture.completedFuture(allProducts);
    }
}
