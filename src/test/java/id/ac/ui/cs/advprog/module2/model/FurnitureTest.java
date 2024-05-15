package id.ac.ui.cs.advprog.module2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FurnitureTest {
    @Test
    public void testFurnitureProperties() {
        Furniture furniture = new Furniture();
        furniture.setName("Chair");
        furniture.setType("Wooden");
        furniture.setDescription("A wooden chair");
        furniture.setOriginalPrice(100.0);
        furniture.setDiscountedPrice(80.0);
        furniture.setHasDiscount(true);

        assertEquals("Chair", furniture.getName());
        assertEquals("Wooden", furniture.getType());
        assertEquals("A wooden chair", furniture.getDescription());
        assertEquals(100.0, furniture.getOriginalPrice());
        assertEquals(80.0, furniture.getDiscountedPrice());
        assertTrue(furniture.isHasDiscount());
    }
}
