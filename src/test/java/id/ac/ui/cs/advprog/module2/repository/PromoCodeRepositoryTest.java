package id.ac.ui.cs.advprog.module2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import id.ac.ui.cs.advprog.module2.model.PromoCode;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PromoCodeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Test
    public void testSavePromoCode() {
        // Create a new PromoCode object
        PromoCode promoCode = new PromoCode();
        promoCode.setName("PROMO1212");
        promoCode.setMinimumPurchase(20);

        // Save the PromoCode object to the repository
        promoCodeRepository.save(promoCode);

        // Retrieve the saved PromoCode from the repository
        PromoCode savedPromoCode = entityManager.find(PromoCode.class, promoCode.getId());

        // Verify that the saved PromoCode is not null
        assertThat(savedPromoCode).isNotNull();

        // Verify that the saved PromoCode has the correct code and discount
        assertThat(savedPromoCode.getName()).isEqualTo("PROMO1212");
        assertThat(savedPromoCode.getMinimumPurchase()).isEqualTo(20);
    }
}
