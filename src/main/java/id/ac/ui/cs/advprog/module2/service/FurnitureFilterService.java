package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import id.ac.ui.cs.advprog.module2.model.FurniturePage;
import org.springframework.data.domain.Page;

public interface FurnitureFilterService {
    public Page<Furniture> getFurnitures(FurnitureFilter furnitureFilter, FurniturePage furniturePage);
}
