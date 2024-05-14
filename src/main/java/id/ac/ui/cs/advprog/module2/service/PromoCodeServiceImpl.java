package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.repository.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

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
            throw new RuntimeException("Furniture with ID " + promoId + " not found");
        }
    }

    @Override
    public void deletePromoCode(Long promoId) {
        promoCodeRepository.deleteById(promoId);
    }

    @Override
    @Async
    public CompletableFuture<PromoCode> getPromoCodeById(Long promoId) {
        Optional<PromoCode> promoOptional = promoCodeRepository.findById(promoId);
        
        if (promoOptional.isPresent()) {
            return CompletableFuture.completedFuture(promoOptional.get());
        } else {
            throw new RuntimeException("Furniture with ID " + promoId + " not found");
        }
    }
}
