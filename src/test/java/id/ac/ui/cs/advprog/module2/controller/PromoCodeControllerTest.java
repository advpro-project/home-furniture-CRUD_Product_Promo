package id.ac.ui.cs.advprog.module2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.PromoCodeServiceImpl;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    @Test
    public void testGetPromoCode() throws Exception {
        // Arrange
        PromoCode promoCode = new PromoCode();

        when(promoCodeService.getPromoCodeById(1L)).thenReturn(CompletableFuture.completedFuture(promoCode));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(promoCodeController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(promoCode);

        // Act & Assert
        mockMvc.perform(get("/promos/get/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testRegisterPromoCode() throws Exception {
        // Arrange
        PromoCode promoCode = new PromoCode();

        when(promoCodeService.addPromoCode(any(PromoCode.class))).thenReturn(promoCode);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(promoCodeController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(promoCode);

        // Act & Assert
        mockMvc.perform(post("/promos/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testUpdatePromoCode() throws Exception {
        // Arrange
        PromoCode promoCode = new PromoCode();

        when(promoCodeService.updatePromoCode(eq(1L), any(PromoCode.class))).thenReturn(promoCode);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(promoCodeController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(promoCode);

        // Act & Assert
        mockMvc.perform(put("/promos/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testDeletePromoCode() throws Exception {
        Long promoId = 1L;
        PromoCode promoCode = new PromoCode();
        promoCode.setInternalId(promoId);

        when(promoCodeService.deletePromoCode(promoId)).thenReturn(promoCode);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(promoCodeController).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(promoCode);

        mockMvc.perform(delete("/promos/delete/" + promoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}