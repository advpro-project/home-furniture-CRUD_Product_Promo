package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Furniture addProduct(Furniture furniture) {
        return productRepository.save(furniture);
    }

    @Override
    public Furniture updateProduct(UUID productId, Furniture newFurniture) {
        Optional<Furniture> existingProductOptional = productRepository.findById(productId);
        
        if (existingProductOptional.isPresent()) {
            Furniture existingFurniture = existingProductOptional.get();

            // Update the existing product with new data
            existingFurniture.setName(newFurniture.getName());
            existingFurniture.setType(newFurniture.getType());
            existingFurniture.setDescription(newFurniture.getDescription());
            existingFurniture.setImageUrl(newFurniture.getImageUrl());
            existingFurniture.setOriginalPrice(newFurniture.getOriginalPrice());
            existingFurniture.setDiscountedPrice(newFurniture.getDiscountedPrice());

            // Save the updated product back to the database
            return productRepository.save(existingFurniture);
        } else {
            // Handle case where product with given ID is not found
            throw new RuntimeException("Furniture with ID " + productId + " not found");
        }
    }

    @Override
    public void deleteProduct(UUID productId) {
        Furniture furniture = getProductById(productId);
        productRepository.delete(furniture);
    }

    @Override
    @Async
    public Furniture getProductById(UUID productId) {
        Optional<Furniture> productOptional = productRepository.findById(productId);
        
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new RuntimeException("Furniture with ID " + productId + " not found");
        }
    }

    @Override
    public List<Furniture> getTop10Products() {
        return productRepository.findFirst10ByOrderBySoldQuantityDesc();
    }

    @Override
    @Async
    public List<Furniture> getAllProducts() {
        return productRepository.findAll();
    }
}
