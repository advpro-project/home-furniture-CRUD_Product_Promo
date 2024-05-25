package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.PromoCodeServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/promos")
public class PromoCodeController {

    @Autowired
    PromoCodeServiceImpl promoCodeService;

    @GetMapping("/all")
    public ResponseEntity<List<PromoCode>> getAllPromoCodes() {
        List<PromoCode> promoCodes = promoCodeService.getAllPromoCodes().join();
        return new ResponseEntity<>(promoCodes, HttpStatus.OK);
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
    public ResponseEntity<PromoCode> registerPromoCode(@RequestBody PromoCode promoCode) {
        PromoCode newPromoCode = promoCodeService.addPromoCode(promoCode);
        return new ResponseEntity<>(newPromoCode, HttpStatus.CREATED);
    }

    @PutMapping("/update/{promoId}")
    public ResponseEntity<PromoCode> updatePromoCode(@PathVariable Long promoId, @RequestBody PromoCode promoCode) {
        PromoCode updatedPromoCode = promoCodeService.updatePromoCode(promoId, promoCode);
        if (updatedPromoCode == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPromoCode, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{promoId}")
    public ResponseEntity<PromoCode> deletePromoCode(@PathVariable Long promoId) {
        PromoCode deletedPromoCode = promoCodeService.deletePromoCode(promoId);
        if (deletedPromoCode == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedPromoCode, HttpStatus.OK);
    }
}
