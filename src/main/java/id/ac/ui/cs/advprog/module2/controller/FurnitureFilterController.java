package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import id.ac.ui.cs.advprog.module2.model.FurniturePage;
import id.ac.ui.cs.advprog.module2.service.FurnitureFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/furniture")
public class FurnitureFilterController {

    @Autowired
    FurnitureFilterService furnitureFilterService;

    @GetMapping("/getFurnitures")
    public ResponseEntity<Page<Furniture>> getFurnitures(@ModelAttribute FurnitureFilter furnitureFilter,
                                                         @ModelAttribute FurniturePage furniturePage)
    {
        return ResponseEntity.ok(furnitureFilterService.getFurnitures(furnitureFilter, furniturePage));
    }
}
