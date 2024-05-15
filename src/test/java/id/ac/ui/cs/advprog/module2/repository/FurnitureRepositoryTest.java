package id.ac.ui.cs.advprog.module2.repository;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class FurnitureRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Test
    public void findFirst10ByOrderBySoldQuantityDesc_ReturnsTop10FurnitureBySoldQuantity() {
        // Arrange
        Furniture furniture1 = new Furniture();
        Furniture furniture2 = new Furniture();
        Furniture furniture3 = new Furniture();

        furniture1.setName("Chair");
        furniture1.setSoldQuantity(10);
        furniture2.setName("Table");
        furniture2.setSoldQuantity(5);
        furniture3.setName("Sofa");
        furniture3.setSoldQuantity(15);

        entityManager.persist(furniture1);
        entityManager.persist(furniture2);
        entityManager.persist(furniture3);
        entityManager.flush();

        // Act
        List<Furniture> top10Furniture = furnitureRepository.findFirst10ByOrderBySoldQuantityDesc();

        // Assert
        assertThat(top10Furniture).hasSize(3);
        assertThat(top10Furniture.get(0)).isEqualTo(furniture3);
        assertThat(top10Furniture.get(1)).isEqualTo(furniture1);
        assertThat(top10Furniture.get(2)).isEqualTo(furniture2);
    }
}
