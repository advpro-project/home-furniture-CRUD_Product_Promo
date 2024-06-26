package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.service.FurnitureServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "https://home-furniture-fe.vercel.app"})
@RestController
@RequestMapping("/furniture")
public class FurnitureController {

    @Autowired
    FurnitureServiceImpl furnitureService;

    @GetMapping("/get/{furnitureId}")
    public ResponseEntity<Furniture> getFurniture(@PathVariable Long furnitureId) {
        Furniture furniture = furnitureService.getFurnitureById(furnitureId).join();
        if (furniture == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(furniture, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Furniture> registerFurniture(@RequestBody Furniture furniture) {
        Furniture newFurniture = furnitureService.addFurniture(furniture);
        return new ResponseEntity<>(newFurniture, HttpStatus.CREATED);
    }

    @PutMapping("/update/{furnitureId}")
    public ResponseEntity<Furniture> updateFurniture(@PathVariable Long furnitureId, @RequestBody Furniture furniture) {
        Furniture updatedFurniture = furnitureService.updateFurniture(furnitureId, furniture);
        if (updatedFurniture == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedFurniture, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{furnitureId}")
    public ResponseEntity<Furniture> deleteFurniture(@PathVariable Long furnitureId) {
        Furniture deletedFurniture = furnitureService.deleteFurniture(furnitureId);
        if (deletedFurniture == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedFurniture, HttpStatus.OK);
    }

    @GetMapping("/statistics/top10")
    public ResponseEntity<List<Furniture>> getTop10PopularFurnitures() {
        List<Furniture> furnitures = furnitureService.getTop10Furnitures();
        return new ResponseEntity<>(furnitures, HttpStatus.OK);
    }
}
