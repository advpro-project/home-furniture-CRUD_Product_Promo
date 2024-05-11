package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.repository.PromoCodeRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
public class PromoCodeServiceTest {

    @Autowired
    private PromoCodeService promoCodeService;

    @MockBean
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @Test
    public void testApplyPromoCode_ValidPromoCode() {

        // Define test data
        String promoCode = "ABC123";
        double discountPercentage = 10.0;

        // Mock behavior of dependencies
        // when(promoCodeRepository.getDiscountPercentage(promoCode)).thenReturn(discountPercentage);
        // when(productService.calculateTotalPrice()).thenReturn(100.0); // Assuming total price is 100.0
        
        // Call the method under test
        // double totalPriceAfterDiscount = promoCodeService.applyPromoCode(promoCode);

        // Verify that the correct methods were called with the correct arguments
        // verify(promoCodeRepository).getDiscountPercentage(promoCode);
        // verify(productService).calculateTotalPrice();
        
        // Verify the result
        double expectedPriceAfterDiscount = 100.0 - (100.0 * discountPercentage / 100.0);
        // assertEquals(expectedPriceAfterDiscount, totalPriceAfterDiscount);
    }
}
