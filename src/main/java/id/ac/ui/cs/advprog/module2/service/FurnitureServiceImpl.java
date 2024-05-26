package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.repository.FurnitureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Optional;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    @Autowired
    FurnitureRepository furnitureRepository;

    @Override
    public Furniture addFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    @Override
    public Furniture updateFurniture(Long furnitureId, Furniture newFurniture) {
        Optional<Furniture> existingFurnitureOptional = furnitureRepository.findById(furnitureId);

        if (existingFurnitureOptional.isPresent()) {
            Furniture existingFurniture = existingFurnitureOptional.get();

            // Update the existing Furniture with new data
            existingFurniture.setName(newFurniture.getName());
            existingFurniture.setType(newFurniture.getType());
            existingFurniture.setDescription(newFurniture.getDescription());
            existingFurniture.setImageUrl(newFurniture.getImageUrl());
            existingFurniture.setOriginalPrice(newFurniture.getOriginalPrice());
            existingFurniture.setDiscountedPrice(newFurniture.getDiscountedPrice());

            // Save the updated Furniture back to the database
            return furnitureRepository.save(existingFurniture);
        } else {
            // Handle case where Furniture with given ID is not found
            return null;
        }
    }

    @Override
    public Furniture deleteFurniture(Long furnitureId) {
        CompletableFuture<Furniture> furnitureFuture = getFurnitureById(furnitureId);

        if (furnitureFuture == null) {
            return null;
        } else {
            furnitureFuture.thenAccept(Furniture -> {
                furnitureRepository.delete(Furniture);
            }).join();
            return furnitureFuture.join();
        }
    }

    @Override
    @Async
    public CompletableFuture<Furniture> getFurnitureById(Long furnitureId) {
        Optional<Furniture> furnitureOptional = furnitureRepository.findById(furnitureId);

        return furnitureOptional.map(CompletableFuture::completedFuture).orElse(null);
    }

    @Override
    public List<Furniture> getTop10Furnitures() {
        return furnitureRepository.findFirst10ByOrderBySoldQuantityDesc();
    }
}
