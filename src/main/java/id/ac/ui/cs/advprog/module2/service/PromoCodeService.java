package id.ac.ui.cs.advprog.module2.service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import id.ac.ui.cs.advprog.module2.model.PromoCode;

public interface PromoCodeService {
    PromoCode addPromoCode(PromoCode promoCode);
    PromoCode updatePromoCode(Long promoId, PromoCode promoCode);
    PromoCode deletePromoCode(Long promoId);
    CompletableFuture<PromoCode> getPromoCodeById(Long promoId);
    CompletableFuture<List<PromoCode>> getAllPromoCodes();
}
