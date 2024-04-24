package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.repository.PromoCodeRepository;

import java.util.UUID;

public class PromoCodeServiceImpl implements PromoCodeService {
    
    private final PromoCodeRepository promoCodeRepository = new PromoCodeRepository();

    @Override
    public PromoCode addPromoCode(PromoCode promoCode) {
        return promoCodeRepository.addPromoCode(promoCode);
    }

    @Override
    public PromoCode updatePromoCode(UUID promoId, PromoCode promoCode) {
        return promoCodeRepository.updatePromoCode(promoId, promoCode);
    }

    @Override
    public void deletePromoCode(UUID promoId) {
        promoCodeRepository.deletePromoCode(promoId);
    }

    @Override
    public PromoCode getPromoCodeById(UUID promoId) {
        return promoCodeRepository.getPromoCodeById(promoId);
    }
}
