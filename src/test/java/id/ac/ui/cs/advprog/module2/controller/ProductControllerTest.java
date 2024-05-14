package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetAllProducts() throws Exception {
        // Arrange
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);
        
        when(productService.getAllProducts()).thenReturn(CompletableFuture.completedFuture(products));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        // Act & Assert
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"));
    }
}