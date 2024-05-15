package id.ac.ui.cs.advprog.module2.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.PromoCodeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;

@WebMvcTest(PromoCodeController.class)
public class PromoCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PromoCodeServiceImpl promoCodeService;

    @InjectMocks
    private PromoCodeController promoCodeController;

    @Test
    public void testGetAllPromoCodes() throws Exception {
        PromoCode promoCode1 = new PromoCode();
        promoCode1.setName("PROMO12");
        promoCode1.setMinimumPurchase(5);

        PromoCode promoCode2 = new PromoCode();
        promoCode1.setName("PROMO34");
        promoCode1.setMinimumPurchase(10);

        List<PromoCode> promoCodes = Arrays.asList(promoCode1, promoCode2);

        when(promoCodeService.getAllPromoCodes()).thenReturn(CompletableFuture.completedFuture(promoCodes));

        mockMvc.perform(MockMvcRequestBuilders.get("/promos/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("PROMO1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].discount").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("PROMO2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].discount").value(20));
    }
}