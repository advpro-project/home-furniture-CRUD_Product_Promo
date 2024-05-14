package id.ac.ui.cs.advprog.module2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.module2.model.Product;
import id.ac.ui.cs.advprog.module2.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void addProduct() {
        // Arrange
        Product product = new Product(); // create a Product object
        when(productRepository.save(product)).thenReturn(product); // mock the save method of the repository

        // Act
        Product savedProduct = productService.addProduct(product); // call the addProduct method

        // Assert
        assertEquals(product, savedProduct); // assert that the returned product is the same as the one passed
        verify(productRepository, times(1)).save(product); // verify that the save method of the repository was called once
    }

    @Test
    void updateProduct() {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product(); // create an existing Product object
        existingProduct.setInternalId(productId); // set ID for the existing product

        Product newProduct = new Product(); // create a new Product object with updated data

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct)); // mock the findById method of the repository
        when(productRepository.save(existingProduct)).thenReturn(existingProduct); // mock the save method of the repository

        // Act
        Product updatedProduct = productService.updateProduct(productId, newProduct); // call the updateProduct method

        // Assert
        assertEquals(newProduct.getInternalId(), updatedProduct.getInternalId()); // assert that the returned product is the same as the new product
        verify(productRepository, times(1)).findById(productId); // verify that the findById method of the repository was called once with the correct ID
        verify(productRepository, times(1)).save(existingProduct); // verify that the save method of the repository was called once with the existing product
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long productId = 1L;
        Product product = new Product(); // create a Product object

        when(productRepository.findById(productId)).thenReturn(Optional.of(product)); // mock the findById method of the repository

        // Act
        productService.deleteProduct(productId); // call the deleteProduct method

        // Assert
        verify(productRepository, times(1)).findById(productId); // verify that the findById method of the repository was called once with the correct ID
        verify(productRepository, times(1)).delete(product); // verify that the delete method of the repository was called once with the correct product
    }

    @Test
    void getProductById_existingProduct() {
        // Arrange
        Long productId = 1L;
        Product product = new Product(); // create a Product object
        when(productRepository.findById(productId)).thenReturn(Optional.of(product)); // mock the findById method of the repository

        // Act
        CompletableFuture<Product> productFuture = productService.getProductById(productId); // call the getProductById method

        // Assert
        assertDoesNotThrow(() -> productFuture.get()); // assert that the CompletableFuture completes successfully
        verify(productRepository, times(1)).findById(productId); // verify that the findById method of the repository was called once with the correct ID
    }

    @Test
    void getProductById_nonExistingProduct() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty()); // mock the findById method of the repository

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.getProductById(productId).join()); // assert that a RuntimeException is thrown when the product is not found
        verify(productRepository, times(1)).findById(productId); // verify that the findById method of the repository was called once with the correct ID
    }

    @Test
    void getTop10Products() {
        // Arrange
        List<Product> topProducts = new ArrayList<>(); // create a list of top products
        when(productRepository.findFirst10ByOrderBySoldQuantityDesc()).thenReturn(topProducts); // mock the findFirst10ByOrderBySoldQuantityDesc method of the repository

        // Act
        List<Product> retrievedTopProducts = productService.getTop10Products(); // call the getTop10Products method

        // Assert
        assertEquals(topProducts, retrievedTopProducts); // assert that the retrieved top products match the mocked list
        verify(productRepository, times(1)).findFirst10ByOrderBySoldQuantityDesc(); // verify that the findFirst10ByOrderBySoldQuantityDesc method of the repository was called once
    }

    @Test
    void getAllProducts() {
        // Arrange
        List<Product> allProducts = new ArrayList<>(); // create a list of all products
        when(productRepository.findAll()).thenReturn(allProducts); // mock the findAll method of the repository

        // Act
        CompletableFuture<List<Product>> allProductsFuture = productService.getAllProducts(); // call the getAllProducts method

        // Assert
        assertDoesNotThrow(() -> allProductsFuture.get()); // assert that the CompletableFuture completes successfully
        assertEquals(allProducts, allProductsFuture.join()); // assert that the retrieved all products match the mocked list
        verify(productRepository, times(1)).findAll(); // verify that the findAll method of the repository was called once
    }
}
