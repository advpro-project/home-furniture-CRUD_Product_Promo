package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.service.FurnitureServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/furniture")
public class FurnitureController {

    @Autowired
    FurnitureServiceImpl FurnitureService;

    @GetMapping("/all")
    public List<Furniture> FurniturePage() {
        return FurnitureService.getAllFurnitures().join();
    }

    @GetMapping("/get/{furnitureId}")
    public Furniture getFurniture(@PathVariable Long furnitureId) {
        return FurnitureService.getFurnitureById(furnitureId).join();
    }

    @PostMapping("/register")
    public Furniture registerFurniture(@RequestBody Furniture furniture) {
        return FurnitureService.addFurniture(furniture);
    }

    @PutMapping("/update/{FurnitureId}")
    public Furniture updateFurniture(@PathVariable Long FurnitureId, @RequestBody Furniture Furniture) {
        return FurnitureService.updateFurniture(FurnitureId, Furniture);
    }

    @DeleteMapping("/delete/{FurnitureId}")
    public void deleteFurniture(@PathVariable Long FurnitureId) {
        FurnitureService.deleteFurniture(FurnitureId);
    }

    @GetMapping("/statistics/top10")
    public List<Furniture> getTop10PopularFurnitures() {
        return FurnitureService.getTop10Furnitures();
    }
}
