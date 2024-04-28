package id.ac.ui.cs.advprog.module2.repository;
import id.ac.ui.cs.advprog.module2.model.PromoCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PromoCodeRepository {

    private List<PromoCode> promoCodes = new ArrayList<>();

    public PromoCode addPromoCode(PromoCode promoCode) {
        promoCode.setId(UUID.randomUUID()); // Assign a UUID to the promo code
        promoCodes.add(promoCode);
        return promoCode;
    }
    public PromoCode updatePromoCode(UUID promoId, PromoCode promoCode) {
        for (int i = 0; i < promoCodes.size(); i++) {
            if (promoCodes.get(i).getId().equals(promoId)) {
                promoCodes.set(i, promoCode);
                return promoCode;
            }
        }
        return null;
    }
    public void deletePromoCode(UUID promoId) {
        promoCodes.removeIf(promoCode -> promoCode.getId().equals(promoId));
    }
    public PromoCode getPromoCodeById(UUID promoId) {
        for (PromoCode promoCode : promoCodes) {
            if (promoCode.getId().equals(promoId)) {
                return promoCode;
            }
        }
        return null;
    }
}
