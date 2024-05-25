package id.ac.ui.cs.advprog.module2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FurniturePageTest {
    FurniturePage furniturePage;

    @BeforeEach
    void setUp() {
        this.furniturePage = new FurniturePage();
    }

    @Test
    void testGetDefaultPageNumber() {
        assertEquals(1, furniturePage.getPageNumber());
    }

    @Test
    void testGetDefaultPageSize() {
        assertEquals(32, furniturePage.getPageSize());
    }

    @Test
    void testGetCustomPageNumber() {
        furniturePage.setPageNumber(2);
        assertEquals(2, furniturePage.getPageNumber());
    }

    @Test
    void testGetCustomPageSize() {
        furniturePage.setPageSize(10);
        assertEquals(10, furniturePage.getPageSize());
    }
}
