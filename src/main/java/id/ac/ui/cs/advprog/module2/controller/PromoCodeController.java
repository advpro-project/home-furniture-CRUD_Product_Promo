package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.PromoCodeServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promos")
public class PromoCodeController {

    @Autowired
    PromoCodeServiceImpl promoCodeService;

    @GetMapping("/all")
    public List<PromoCode> promoPage() {
        return promoCodeService.getAllPromoCodes().join();
    }

    @GetMapping("/get/{promoId}")
    public ResponseEntity<PromoCode> getPromoCode(@PathVariable Long promoId) {
        PromoCode promoCode = promoCodeService.getPromoCodeById(promoId).join();
        if (promoCode == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(promoCode, HttpStatus.OK);
    }

    @PostMapping("/register")
    public PromoCode registerPromoCode(@RequestBody PromoCode promoCode) {
        return promoCodeService.addPromoCode(promoCode);
    }

    @PutMapping("/update/{promoId}")
    public PromoCode updatePromoCode(@PathVariable Long promoId, @RequestBody PromoCode promoCode) {
        return promoCodeService.updatePromoCode(promoId, promoCode);
    }

    @DeleteMapping("/delete/{promoId}")
    public void deletePromoCode(@PathVariable Long promoId) {
        promoCodeService.deletePromoCode(promoId);
    }
}
