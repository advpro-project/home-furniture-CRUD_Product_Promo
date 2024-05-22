package id.ac.ui.cs.advprog.module2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.repository.FurnitureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FurnitureServiceTest {

    @Mock
    private FurnitureRepository furnitureRepository;

    @InjectMocks
    private FurnitureServiceImpl furnitureService;

    @Test
    void addFurniture() {
        // Arrange
        Furniture Furniture = new Furniture(); // create a Furniture object
        when(furnitureRepository.save(Furniture)).thenReturn(Furniture); // mock the save method of the repository

        // Act
        Furniture savedFurniture = furnitureService.addFurniture(Furniture); // call the addFurniture method

        // Assert
        assertEquals(Furniture, savedFurniture); // assert that the returned Furniture is the same as the one passed
        verify(furnitureRepository, times(1)).save(Furniture); // verify that the save method of the repository was called once
    }

    @Test
    void updateFurniture() {
        // Arrange
        Long furnitureId = 1L;
        Furniture newFurniture = new Furniture();
        Furniture existingFurniture = new Furniture();

        when(furnitureRepository.findById(furnitureId)).thenReturn(Optional.of(existingFurniture));
        when(furnitureRepository.save(existingFurniture)).thenReturn(existingFurniture);

        assertEquals(existingFurniture, furnitureService.updateFurniture(furnitureId, newFurniture));
    }

    @Test
    void deleteFurniture() {
        // Arrange
        Long FurnitureId = 1L;
        Furniture Furniture = new Furniture(); // create a Furniture object

        when(furnitureRepository.findById(FurnitureId)).thenReturn(Optional.of(Furniture)); // mock the findById method of the repository

        // Act
        furnitureService.deleteFurniture(FurnitureId); // call the deleteFurniture method

        // Assert
        verify(furnitureRepository, times(1)).findById(FurnitureId); // verify that the findById method of the repository was called once with the correct ID
        verify(furnitureRepository, times(1)).delete(Furniture); // verify that the delete method of the repository was called once with the correct Furniture
    }

    @Test
    void getFurnitureById_existingFurniture() {
        // Arrange
        Long FurnitureId = 1L;
        Furniture Furniture = new Furniture(); // create a Furniture object
        when(furnitureRepository.findById(FurnitureId)).thenReturn(Optional.of(Furniture)); // mock the findById method of the repository

        // Act
        CompletableFuture<Furniture> FurnitureFuture = furnitureService.getFurnitureById(FurnitureId); // call the getFurnitureById method

        // Assert
        assertDoesNotThrow(() -> FurnitureFuture.get()); // assert that the CompletableFuture completes successfully
        verify(furnitureRepository, times(1)).findById(FurnitureId); // verify that the findById method of the repository was called once with the correct ID
    }

    @Test
    void getFurnitureById_nonExistingFurniture() {
        // Arrange
        Long FurnitureId = 1L;
        when(furnitureRepository.findById(FurnitureId)).thenReturn(Optional.empty()); // mock the findById method of the repository

        // Act & Assert
        assertThrows(RuntimeException.class, () -> furnitureService.getFurnitureById(FurnitureId).join()); // assert that a RuntimeException is thrown when the Furniture is not found
        verify(furnitureRepository, times(1)).findById(FurnitureId); // verify that the findById method of the repository was called once with the correct ID
    }

    @Test
    void getTop10Furnitures() {
        // Arrange
        List<Furniture> topFurnitures = new ArrayList<>(); // create a list of top Furnitures
        when(furnitureRepository.findFirst10ByOrderBySoldQuantityDesc()).thenReturn(topFurnitures); // mock the findFirst10ByOrderBySoldQuantityDesc method of the repository

        // Act
        List<Furniture> retrievedTopFurnitures = furnitureService.getTop10Furnitures(); // call the getTop10Furnitures method

        // Assert
        assertEquals(topFurnitures, retrievedTopFurnitures); // assert that the retrieved top Furnitures match the mocked list
        verify(furnitureRepository, times(1)).findFirst10ByOrderBySoldQuantityDesc(); // verify that the findFirst10ByOrderBySoldQuantityDesc method of the repository was called once
    }
}
