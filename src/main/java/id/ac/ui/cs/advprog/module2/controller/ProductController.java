package id.ac.ui.cs.advprog.module2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("")
    public String authPage(Model model){
        return "authPage";
    }
}
