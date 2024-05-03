package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testGetProductById() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(productId);

        assertEquals(productId, result.getId());
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(products.size(), result.size());
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        productService.addProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        UUID productId = UUID.randomUUID();
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("Updated Product");

        productService.updateProduct(productId, updatedProduct);

        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    public void testDeleteProduct() {
        UUID productId = UUID.randomUUID();
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }
}
