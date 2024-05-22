package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.model.Furniture;

import java.util.concurrent.CompletableFuture;
import java.util.List;

public interface FurnitureService {
    Furniture addFurniture(Furniture furniture);
    Furniture updateFurniture(Long FurnitureId, Furniture furniture);
    Furniture deleteFurniture(Long FurnitureId);
    CompletableFuture<Furniture> getFurnitureById(Long FurnitureId);
    List<Furniture> getTop10Furnitures();
}
