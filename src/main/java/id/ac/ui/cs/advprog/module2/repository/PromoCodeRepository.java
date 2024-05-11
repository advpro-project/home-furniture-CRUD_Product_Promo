package id.ac.ui.cs.advprog.module2.repository;
import id.ac.ui.cs.advprog.module2.model.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, UUID> {
}
