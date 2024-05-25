package id.ac.ui.cs.advprog.module2.repository;


import id.ac.ui.cs.advprog.module2.Module2Application;
import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import id.ac.ui.cs.advprog.module2.model.FurniturePage;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Module2Application.class)
@ActiveProfiles("test")
@Transactional
public class FurnitureFilterRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FurnitureFilterRepository furnitureFilterRepository;

    FurnitureFilter furnitureFilter;
    FurniturePage furniturePage;

    @BeforeEach
    void setUp() {
        this.furnitureFilter = new FurnitureFilter();
        this.furnitureFilter.setName("Furniture 1");
        this.furnitureFilter.setType("Type A");
        this.furnitureFilter.setDiscount(true);
        this.furnitureFilter.setPriceOrder("max");

        this.furniturePage = new FurniturePage();

        // make dummy data that want to be retrieved from db
        Furniture furniture1 = new Furniture();
        furniture1.setName("Furniture 1");
        furniture1.setType("Type A");
        furniture1.setHasDiscount(true);
        furniture1.setDiscountedPrice(100);
        this.entityManager.persist(furniture1);

        Furniture furniture2 = new Furniture();
        furniture2.setName("Furniture 1");
        furniture2.setType("Type A");
        furniture2.setHasDiscount(true);
        furniture2.setDiscountedPrice(500);
        this.entityManager.persist(furniture2);

        Furniture furniture3 = new Furniture();
        furniture3.setName("Furniture 1");
        furniture3.setType("Type A");
        furniture3.setDiscountedPrice(1000);
        furniture3.setHasDiscount(true);
        this.entityManager.persist(furniture3);

        this.entityManager.flush();
    }

    @Test
    void testFindAllWithFilter() {
        Page<Furniture> pages = furnitureFilterRepository.findAllWithFilter(furnitureFilter, furniturePage);
        logger.info("Page Contains -> {}", pages);

        assertEquals(1, pages.getNumber());
        assertEquals(32, pages.getSize());
        assertEquals(3, pages.getTotalElements());
        assertEquals(1, pages.getTotalPages());
    }
}
