package id.ac.ui.cs.advprog.module2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FurnitureFilterTest {
    FurnitureFilter furnitureFilter;

    @BeforeEach
    void setUp() {
        this.furnitureFilter = new FurnitureFilter();
        this.furnitureFilter.setName("Item 1");
        this.furnitureFilter.setType("Type A");
        this.furnitureFilter.setDiscount(true);
        this.furnitureFilter.setPriceOrder("max");
    }

    @Test
    void testGetName() {
        assertEquals("Item 1", furnitureFilter.getName());
    }

    @Test
    void testGetType() {
        assertEquals("Type A", furnitureFilter.getType());
    }

    @Test
    void testGetDiscount() {
        assertTrue(furnitureFilter.isDiscount());
    }

    @Test
    void testGetPrice() {
        assertEquals("max", furnitureFilter.getPriceOrder());
    }
}
