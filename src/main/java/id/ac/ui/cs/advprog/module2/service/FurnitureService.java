package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.model.Furniture;

import java.util.concurrent.CompletableFuture;
import java.util.List;

public interface FurnitureService {
    Furniture addFurniture(Furniture furniture);
    Furniture updateFurniture(Long furnitureId, Furniture furniture);
    Furniture deleteFurniture(Long furnitureId);
    CompletableFuture<Furniture> getFurnitureById(Long furnitureId);
    List<Furniture> getTop10Furnitures();
}
