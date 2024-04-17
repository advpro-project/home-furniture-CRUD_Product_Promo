package id.ac.ui.cs.advprog.module2.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private UUID id;
    private String name;
    private String category;
    private String description;
    private String image;
    private double originalPrice;
    private double discountedPrice;
}