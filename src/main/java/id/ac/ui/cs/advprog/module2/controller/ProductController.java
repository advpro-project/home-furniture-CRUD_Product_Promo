package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.service.ProductServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("")
    public String productPage(Model model) {
        List<Furniture> allFurnitures = productService.getAllProducts();
        model.addAttribute("products", allFurnitures);
        return "ProductPage";
    }

    @PostMapping("/register")
    public Furniture registerProduct(@RequestBody Furniture furniture) {
        return productService.addProduct(furniture);
    }

    @PutMapping("/{productId}")
    public Furniture updateProduct(@PathVariable UUID productId, @RequestBody Furniture furniture) {
        return productService.updateProduct(productId, furniture);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/statistics/top10")
    public List<Furniture> getTop10PopularProducts() {
        return productService.getTop10Products();
    }
}
