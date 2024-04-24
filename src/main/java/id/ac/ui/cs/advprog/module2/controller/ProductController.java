package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.service.ProductServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService = new ProductServiceImpl();

    @GetMapping("")
    public String authPage(Model model){
        return "authPage";
    }

    @PostMapping("/register")
    public Product registerProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable UUID productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/statistics/top10")
    public List<Product> getTop10PopularProducts() {
        return productService.getTop10PopularProducts();
    }
}
