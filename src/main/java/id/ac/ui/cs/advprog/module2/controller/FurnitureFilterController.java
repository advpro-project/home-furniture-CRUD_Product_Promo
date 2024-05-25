package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import id.ac.ui.cs.advprog.module2.model.FurniturePage;
import id.ac.ui.cs.advprog.module2.service.FurnitureFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "https://home-furniture-fe.vercel.app"})
@RestController
@RequestMapping("/furniture")
public class FurnitureFilterController {

    @Autowired
    FurnitureFilterService furnitureFilterService;

    @GetMapping("/list")
    public ResponseEntity<Page<Furniture>> getFurnitures(@ModelAttribute FurnitureFilter furnitureFilter,
                                                         @ModelAttribute FurniturePage furniturePage)
    {
        if (furniturePage.getPageNumber() < 1) {
            return ResponseEntity.badRequest().build();
        }

        furniturePage.setPageNumber(furniturePage.getPageNumber() - 1);

        return ResponseEntity.ok(furnitureFilterService.getFurnitures(furnitureFilter, furniturePage));
    }
}
