package id.ac.ui.cs.advprog.module2.service;
import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class FurnitureServiceTest {

    @Autowired
    ProductServiceImpl productService;

    @MockBean
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testGetProductById() {
        UUID productId = UUID.randomUUID();
        Furniture furniture = new Furniture();
        furniture.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(furniture));

        Furniture result = productService.getProductById(productId);

        assertEquals(productId, result.getId());
    }

    @Test
    public void testGetAllProducts() {
        Furniture furniture1 = new Furniture();
        Furniture furniture2 = new Furniture();
        List<Furniture> furnitures = Arrays.asList(furniture1, furniture2);
        when(productRepository.findAll()).thenReturn(furnitures);

        List<Furniture> result = productService.getAllProducts();

        assertEquals(furnitures.size(), result.size());
    }

    @Test
    public void testAddProduct() {
        Furniture furniture = new Furniture();
        productService.addProduct(furniture);
        verify(productRepository, times(1)).save(furniture);
    }

    @Test
    public void testUpdateProduct() {
        UUID productId = UUID.randomUUID();
        Furniture existingFurniture = new Furniture();
        existingFurniture.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingFurniture));

        Furniture updatedFurniture = new Furniture();
        updatedFurniture.setId(productId);
        updatedFurniture.setName("Updated Furniture");

        productService.updateProduct(productId, updatedFurniture);

        verify(productRepository, times(1)).save(updatedFurniture);
    }

    @Test
    public void testDeleteProduct() {
        UUID productId = UUID.randomUUID();
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }
}
