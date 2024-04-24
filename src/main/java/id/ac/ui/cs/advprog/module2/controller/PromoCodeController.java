package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.PromoCodeServiceImpl;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/promos")
public class PromoCodeController {

    private final PromoCodeServiceImpl promoCodeService = new PromoCodeServiceImpl();

    @PostMapping("/register")
    public PromoCode registerPromoCode(@RequestBody PromoCode promoCode) {
        return promoCodeService.addPromoCode(promoCode);
    }

    @PutMapping("/{promoId}")
    public PromoCode updatePromoCode(@PathVariable UUID promoId, @RequestBody PromoCode promoCode) {
        return promoCodeService.updatePromoCode(promoId, promoCode);
    }

    @DeleteMapping("/{promoId}")
    public void deletePromoCode(@PathVariable UUID promoId) {
        promoCodeService.deletePromoCode(promoId);
    }
}
