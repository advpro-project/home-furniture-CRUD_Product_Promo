package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.service.FurnitureServiceImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {

    @Autowired
    FurnitureServiceImpl FurnitureService;

    @GetMapping("/all")
    public String FurniturePage(Model model) {
        CompletableFuture<List<Furniture>> FurnitureFuture = FurnitureService.getAllFurnitures();

        FurnitureFuture.thenAccept(FurnitureList -> {
            model.addAttribute("Furnitures", FurnitureList);
        }).join();

        return "FurniturePage";
    }

    @PostMapping("/register")
    public Furniture registerFurniture(@RequestBody Furniture furniture) {
        return FurnitureService.addFurniture(furniture);
    }

    @PutMapping("/{FurnitureId}")
    public Furniture updateFurniture(@PathVariable Long FurnitureId, @RequestBody Furniture Furniture) {
        return FurnitureService.updateFurniture(FurnitureId, Furniture);
    }

    @DeleteMapping("/{FurnitureId}")
    public void deleteFurniture(@PathVariable Long FurnitureId) {
        FurnitureService.deleteFurniture(FurnitureId);
    }

    @GetMapping("/statistics/top10")
    public List<Furniture> getTop10PopularFurnitures() {
        return FurnitureService.getTop10Furnitures();
    }
}
