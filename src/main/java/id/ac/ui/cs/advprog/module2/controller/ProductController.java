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
@RequestMapping("/")
public class ProductController {

    @Autowired
    FurnitureServiceImpl productService;

    @GetMapping("")
    public String productPage(Model model) {
        CompletableFuture<List<Product>> productFuture = productService.getAllProducts();

        productFuture.thenAccept(productList -> {
            model.addAttribute("products", productList);
        }).join();

        return "ProductPage";
    }

    @PostMapping("/register")
    public Furniture registerProduct(@RequestBody Furniture furniture) {
        return productService.addProduct(furniture);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/statistics/top10")
    public List<Furniture> getTop10PopularProducts() {
        return productService.getTop10Products();
    }
}
