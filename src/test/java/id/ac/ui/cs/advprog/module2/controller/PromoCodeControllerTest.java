package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.PromoCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
@AutoConfigureMockMvc
public class PromoCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromoCodeService promoCodeService;

    @BeforeEach
    public void setup() {
        // Initialize your mock service here if needed
    }

    @Test
    public void whenGetPromoCode_returnOk() throws Exception {
        // Assuming the method takes a promo code ID as a parameter
        Long promoCodeId = 1L;

        // Mock the service call
        when(promoCodeService.getPromoCodeById(promoCodeId)).thenReturn(new CompletableFuture<PromoCode>());

        // Perform the GET request
        mockMvc.perform(get("/promoCode/" + promoCodeId))
                .andExpect(status().isOk());
    }
}