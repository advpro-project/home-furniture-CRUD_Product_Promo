package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.repository.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.Optional;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {
    
    @Autowired
    PromoCodeRepository promoCodeRepository;

    @Override
    public PromoCode addPromoCode(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    @Override
    public PromoCode updatePromoCode(Long promoId, PromoCode promoCode) {
        Optional<PromoCode> existingPromoOptional = promoCodeRepository.findById(promoId);
        
        if (existingPromoOptional.isPresent()) {
            PromoCode existingPromo = existingPromoOptional.get();

            // Update the existing product with new data
            existingPromo.setName(promoCode.getName());
            existingPromo.setDescription(promoCode.getDescription());
            existingPromo.setMinimumPurchase(promoCode.getMinimumPurchase());
            existingPromo.setValidUntil(promoCode.getValidUntil());

            // Save the updated product back to the database
            return promoCodeRepository.save(existingPromo);
        } else {
            // Handle case where product with given ID is not found
            return null;
        }
    }

    @Override
    public PromoCode deletePromoCode(Long promoId) {
        Optional<PromoCode> existingPromoOptional = promoCodeRepository.findById(promoId);

        if (existingPromoOptional.isPresent()) {
            PromoCode existingPromo = existingPromoOptional.get();
            promoCodeRepository.deleteById(promoId);
            return(existingPromo);
        } else {
            return null;
        }
    }

    @Override
    @Async
    public CompletableFuture<PromoCode> getPromoCodeById(Long promoId) {
        Optional<PromoCode> promoOptional = promoCodeRepository.findById(promoId);

        return promoOptional.map(CompletableFuture::completedFuture).orElse(null);
    }

    @Override
    @Async
    public CompletableFuture<List<PromoCode>> getAllPromoCodes() {
        return CompletableFuture.completedFuture(promoCodeRepository.findAll());
    }
}
