package id.ac.ui.cs.advprog.module2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PromoCodeTest {
    @Test
    public void testPromoCodeProperties() {
        PromoCode promoCode = new PromoCode();
        promoCode.setName("PROMO10");
        promoCode.setDescription("10% discount");

        assertEquals("PROMO10", promoCode.getName());
        assertEquals(10.0, promoCode.getDescription());
    }
}
