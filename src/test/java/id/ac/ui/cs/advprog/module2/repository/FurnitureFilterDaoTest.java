package id.ac.ui.cs.advprog.module2.repository;

import id.ac.ui.cs.advprog.module2.Module2Application;
import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Module2Application.class)
@ActiveProfiles("test")
@Transactional
public class FurnitureFilterDaoTest {

    @Autowired
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FurnitureFilterDao furnitureFilterDao;

    FurnitureFilter furnitureFilter;

    @BeforeEach
    void setUp() {
        this.furnitureFilter = new FurnitureFilter();
        this.furnitureFilter.setName("Furniture 1");
        this.furnitureFilter.setType("Type A");
        this.furnitureFilter.setDiscount(true);
        this.furnitureFilter.setPriceOrder("max");

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
    }

    @Test
    void testBuildFurnitureFilterQueryOrderByMinPrice() {
        TypedQuery<Furniture> query = furnitureFilterDao
                .buildFurnitureFilterQuery(furnitureFilter);

        assertNotNull(query);

        List<Furniture> resultList = query.getResultList();

        // check if the list sorted by DiscountedPrice DESC
        assertTrue(resultList.get(0).getDiscountedPrice() >
                resultList.get(1).getDiscountedPrice() && resultList.get(1).getDiscountedPrice() >
                resultList.get(2).getDiscountedPrice());
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    void testBuildFurnitureFilterQueryOrderByMaxPrice() {
        furnitureFilter.setPriceOrder("min");
        TypedQuery<Furniture> query = furnitureFilterDao
                .buildFurnitureFilterQuery(furnitureFilter);

        assertNotNull(query);

        // check if the list sorted by DiscountedPrice ASC
        List<Furniture> resultList = query.getResultList();
        assertTrue(resultList.get(0).getDiscountedPrice() <
                resultList.get(1).getDiscountedPrice() && resultList.get(1).getDiscountedPrice() <
                resultList.get(2).getDiscountedPrice());
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    void testBuildFurnitureFilterQuery_DefaultFilter() {
        furnitureFilter.setName(null);
        furnitureFilter.setType(null);
        furnitureFilter.setDiscount(false);
        furnitureFilter.setPriceOrder(null);
        TypedQuery<Furniture> query = furnitureFilterDao
                .buildFurnitureFilterQuery(furnitureFilter);

        assertNotNull(query);

        List<Furniture> resultList = query.getResultList();
        assertEquals(3, resultList.size());
        logger.info("Typed Query -> {}", resultList);

    }

    @Test
    void testBuildCountFurnitureQuery() {
        Long itemRetrievedCount = furnitureFilterDao.buildCountFurnitureQuery(furnitureFilter);

        assertEquals(3L, itemRetrievedCount);
    }

    @Test
    void testBuildCountFurnitureQuery_DefaultFilter() {
        furnitureFilter.setName(null);
        furnitureFilter.setType(null);
        furnitureFilter.setDiscount(false);
        furnitureFilter.setPriceOrder(null);
        Long itemRetrievedCount = furnitureFilterDao.buildCountFurnitureQuery(furnitureFilter);

        assertEquals(3L, itemRetrievedCount);
    }
}
