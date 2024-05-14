package id.ac.ui.cs.advprog.module2.controller;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.service.FurnitureServiceImpl;
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
public class FurnitureControllerTest {

    @Mock
    private FurnitureServiceImpl furnitureService;

    @InjectMocks
    private FurnitureController furnitureController;

    @Test
    public void testGetAllFurnitures() throws Exception {
        // Arrange
        Furniture Furniture1 = new Furniture();
        Furniture Furniture2 = new Furniture();
        List<Furniture> Furnitures = Arrays.asList(Furniture1, Furniture2);
        
        when(furnitureService.getAllFurnitures()).thenReturn(CompletableFuture.completedFuture(Furnitures));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(furnitureController).build();

        // Act & Assert
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("FurniturePage"));
    }
}