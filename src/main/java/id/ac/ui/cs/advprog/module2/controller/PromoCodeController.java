package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.PromoCodeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/promos")
public class PromoCodeController {

    @Autowired
    PromoCodeServiceImpl promoCodeService;

    @PostMapping("/register")
    public PromoCode registerPromoCode(@RequestBody PromoCode promoCode) {
        return promoCodeService.addPromoCode(promoCode);
    }

    @PutMapping("/{promoId}")
    public PromoCode updatePromoCode(@PathVariable Long promoId, @RequestBody PromoCode promoCode) {
        return promoCodeService.updatePromoCode(promoId, promoCode);
    }

    @DeleteMapping("/{promoId}")
    public void deletePromoCode(@PathVariable Long promoId) {
        promoCodeService.deletePromoCode(promoId);
    }
}
