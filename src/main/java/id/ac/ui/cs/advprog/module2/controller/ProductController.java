package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.service.ProductServiceImpl;

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
    ProductServiceImpl productService;

    @GetMapping("")
    public String productPage(Model model) {
        CompletableFuture<List<Product>> productFuture = productService.getAllProducts();

        productFuture.thenAccept(productList -> {
            model.addAttribute("products", productList);
        }).join();

        return "ProductPage";
    }

    @PostMapping("/register")
    public Product registerProduct(@RequestBody Product product) {
        return productService.addProduct(product);
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
    public List<Product> getTop10PopularProducts() {
        return productService.getTop10Products();
    }
}
