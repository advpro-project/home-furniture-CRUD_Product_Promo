package id.ac.ui.cs.advprog.module2.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl();

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        when(productRepository.addProduct(product)).thenReturn(product);
        assertEquals(product, productService.addProduct(product));
    }

    @Test
    void testUpdateProduct() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(productRepository.updateProduct(productId, product)).thenReturn(product);
        assertEquals(product, productService.updateProduct(productId, product));
    }

    @Test
    void testDeleteProduct() {
        UUID productId = UUID.randomUUID();
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteProduct(productId);
    }

    @Test
    void testGetProductById() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(productRepository.getProductById(productId)).thenReturn(product);
        assertEquals(product, productService.getProductById(productId));
    }

    @Test
    void testGetTop10PopularProducts() {
        // Simulate test data
        List<Product> topProducts = new ArrayList<Product>();
        
        when(productRepository.getTop10PopularProducts()).thenReturn(topProducts);
        assertEquals(topProducts, productService.getTop10PopularProducts());
    }
}
