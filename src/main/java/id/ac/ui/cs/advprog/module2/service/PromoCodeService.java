package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.model.PromoCode;

import java.util.UUID;

public interface PromoCodeService {
    PromoCode addPromoCode(PromoCode promoCode);
    PromoCode updatePromoCode(UUID promoId, PromoCode promoCode);
    void deletePromoCode(UUID promoId);
    PromoCode getPromoCodeById(UUID promoId);
}
