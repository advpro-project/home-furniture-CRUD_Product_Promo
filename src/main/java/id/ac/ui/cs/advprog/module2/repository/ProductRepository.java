package id.ac.ui.cs.advprog.module2.repository;
import id.ac.ui.cs.advprog.module2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FurnitureRepository extends JpaRepository<Product, UUID> {
    List<Product> findFirst10ByOrderBySoldQuantityDesc();
}
