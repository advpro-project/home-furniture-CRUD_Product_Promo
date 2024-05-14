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
    private FurnitureRepository FurnitureRepository;

    @InjectMocks
    private FurnitureServiceImpl FurnitureService;

    @Test
    void addFurniture() {
        // Arrange
        Furniture Furniture = new Furniture(); // create a Furniture object
        when(FurnitureRepository.save(Furniture)).thenReturn(Furniture); // mock the save method of the repository

        // Act
        Furniture savedFurniture = FurnitureService.addFurniture(Furniture); // call the addFurniture method

        // Assert
        assertEquals(Furniture, savedFurniture); // assert that the returned Furniture is the same as the one passed
        verify(FurnitureRepository, times(1)).save(Furniture); // verify that the save method of the repository was called once
    }

    @Test
    void updateFurniture() {
        // Arrange
        Long FurnitureId = 1L;
        Furniture existingFurniture = new Furniture(); // create an existing Furniture object
        existingFurniture.setInternalId(FurnitureId); // set ID for the existing Furniture

        Furniture newFurniture = new Furniture(); // create a new Furniture object with updated data

        when(FurnitureRepository.findById(FurnitureId)).thenReturn(Optional.of(existingFurniture)); // mock the findById method of the repository
        when(FurnitureRepository.save(existingFurniture)).thenReturn(existingFurniture); // mock the save method of the repository

        // Act
        Furniture updatedFurniture = FurnitureService.updateFurniture(FurnitureId, newFurniture); // call the updateFurniture method

        // Assert
        assertEquals(newFurniture.getInternalId(), updatedFurniture.getInternalId()); // assert that the returned Furniture is the same as the new Furniture
        verify(FurnitureRepository, times(1)).findById(FurnitureId); // verify that the findById method of the repository was called once with the correct ID
        verify(FurnitureRepository, times(1)).save(existingFurniture); // verify that the save method of the repository was called once with the existing Furniture
    }

    @Test
    void deleteFurniture() {
        // Arrange
        Long FurnitureId = 1L;
        Furniture Furniture = new Furniture(); // create a Furniture object

        when(FurnitureRepository.findById(FurnitureId)).thenReturn(Optional.of(Furniture)); // mock the findById method of the repository

        // Act
        FurnitureService.deleteFurniture(FurnitureId); // call the deleteFurniture method

        // Assert
        verify(FurnitureRepository, times(1)).findById(FurnitureId); // verify that the findById method of the repository was called once with the correct ID
        verify(FurnitureRepository, times(1)).delete(Furniture); // verify that the delete method of the repository was called once with the correct Furniture
    }

    @Test
    void getFurnitureById_existingFurniture() {
        // Arrange
        Long FurnitureId = 1L;
        Furniture Furniture = new Furniture(); // create a Furniture object
        when(FurnitureRepository.findById(FurnitureId)).thenReturn(Optional.of(Furniture)); // mock the findById method of the repository

        // Act
        CompletableFuture<Furniture> FurnitureFuture = FurnitureService.getFurnitureById(FurnitureId); // call the getFurnitureById method

        // Assert
        assertDoesNotThrow(() -> FurnitureFuture.get()); // assert that the CompletableFuture completes successfully
        verify(FurnitureRepository, times(1)).findById(FurnitureId); // verify that the findById method of the repository was called once with the correct ID
    }

    @Test
    void getFurnitureById_nonExistingFurniture() {
        // Arrange
        Long FurnitureId = 1L;
        when(FurnitureRepository.findById(FurnitureId)).thenReturn(Optional.empty()); // mock the findById method of the repository

        // Act & Assert
        assertThrows(RuntimeException.class, () -> FurnitureService.getFurnitureById(FurnitureId).join()); // assert that a RuntimeException is thrown when the Furniture is not found
        verify(FurnitureRepository, times(1)).findById(FurnitureId); // verify that the findById method of the repository was called once with the correct ID
    }

    @Test
    void getTop10Furnitures() {
        // Arrange
        List<Furniture> topFurnitures = new ArrayList<>(); // create a list of top Furnitures
        when(FurnitureRepository.findFirst10ByOrderBySoldQuantityDesc()).thenReturn(topFurnitures); // mock the findFirst10ByOrderBySoldQuantityDesc method of the repository

        // Act
        List<Furniture> retrievedTopFurnitures = FurnitureService.getTop10Furnitures(); // call the getTop10Furnitures method

        // Assert
        assertEquals(topFurnitures, retrievedTopFurnitures); // assert that the retrieved top Furnitures match the mocked list
        verify(FurnitureRepository, times(1)).findFirst10ByOrderBySoldQuantityDesc(); // verify that the findFirst10ByOrderBySoldQuantityDesc method of the repository was called once
    }

    @Test
    void getAllFurnitures() {
        // Arrange
        List<Furniture> allFurnitures = new ArrayList<>(); // create a list of all Furnitures
        when(FurnitureRepository.findAll()).thenReturn(allFurnitures); // mock the findAll method of the repository

        // Act
        CompletableFuture<List<Furniture>> allFurnituresFuture = FurnitureService.getAllFurnitures(); // call the getAllFurnitures method

        // Assert
        assertDoesNotThrow(() -> allFurnituresFuture.get()); // assert that the CompletableFuture completes successfully
        assertEquals(allFurnitures, allFurnituresFuture.join()); // assert that the retrieved all Furnitures match the mocked list
        verify(FurnitureRepository, times(1)).findAll(); // verify that the findAll method of the repository was called once
    }
}
