package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.repository.PromoCodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PromoCodeServiceTest {

    @Autowired
    private PromoCodeService promoCodeService;

    @MockBean
    private PromoCodeRepository promoCodeRepository;

    @Test
    public void testAddPromoCode() {
        PromoCode promoCode = new PromoCode();
        when(promoCodeRepository.save(promoCode)).thenReturn(promoCode);

        assertEquals(promoCode, promoCodeService.addPromoCode(promoCode));
    }

    @Test
    public void testUpdatePromoCode() throws ExecutionException, InterruptedException {
        Long promoId = 1L;
        PromoCode newPromoCode = new PromoCode();
        PromoCode existingPromoCode = new PromoCode();

        when(promoCodeRepository.findById(promoId)).thenReturn(Optional.of(existingPromoCode));
        when(promoCodeRepository.save(existingPromoCode)).thenReturn(existingPromoCode);

        assertEquals(existingPromoCode, promoCodeService.updatePromoCode(promoId, newPromoCode));
    }

    @Test
    public void testUpdatePromoCode_NotFound() {
        Long promoId = 1L;
        PromoCode newPromoCode = new PromoCode();

        when(promoCodeRepository.findById(promoId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> promoCodeService.updatePromoCode(promoId, newPromoCode));
    }

    @Test
    public void testGetPromoCodeById() throws ExecutionException, InterruptedException {
        Long promoId = 1L;
        PromoCode promoCode = new PromoCode();

        when(promoCodeRepository.findById(promoId)).thenReturn(Optional.of(promoCode));

        CompletableFuture<PromoCode> promoCodeFuture = promoCodeService.getPromoCodeById(promoId);
        assertEquals(promoCode, promoCodeFuture.get());
    }

    @Test
    public void testDeletePromoCode() {
        Long promoId = 1L;

        doNothing().when(promoCodeRepository).deleteById(promoId);

        promoCodeService.deletePromoCode(promoId);

        verify(promoCodeRepository, times(1)).deleteById(promoId);
    }
}