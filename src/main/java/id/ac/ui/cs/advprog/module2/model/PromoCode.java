package id.ac.ui.cs.advprog.module2.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PromoCode {
    private UUID id;
    private String name;
    private String description;
    private double minimumPurchase;
    private String validUntil;
}
