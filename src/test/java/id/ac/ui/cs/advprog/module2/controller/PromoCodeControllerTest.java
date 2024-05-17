package id.ac.ui.cs.advprog.module2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.PromoCodeServiceImpl;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PromoCodeControllerTest {

    @Mock
    private PromoCodeServiceImpl promoCodeService;

    @InjectMocks
    private PromoCodeController promoCodeController;

    @Test
    public void testGetAllPromoCodes() throws Exception {
        // Arrange
        PromoCode Furniture1 = new PromoCode();
        PromoCode Furniture2 = new PromoCode();
        List<PromoCode> promoCodes = Arrays.asList(Furniture1, Furniture2);

        when(promoCodeService.getAllPromoCodes()).thenReturn(CompletableFuture.completedFuture(promoCodes));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(promoCodeController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(promoCodes);

        // Act & Assert
        mockMvc.perform(get("/promos/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}