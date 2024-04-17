package id.ac.ui.cs.advprog.module2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class ProductController {

    @GetMapping("")
    public String productPage(Model model){
        return "ProductPage";
    }
}
