package id.ac.ui.cs.advprog.module2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.PromoCode;
import id.ac.ui.cs.advprog.module2.service.FurnitureServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
        List<Furniture> furnitures = Arrays.asList(Furniture1, Furniture2);
        
        when(furnitureService.getAllFurnitures()).thenReturn(CompletableFuture.completedFuture(furnitures));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(furnitureController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(furnitures);

        // Act & Assert
        mockMvc.perform(get("/furniture/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testGetTop10PopularFurnitures() throws Exception {
        // Arrange
        Furniture Furniture1 = new Furniture();
        Furniture Furniture2 = new Furniture();
        List<Furniture> furnitures = Arrays.asList(Furniture1, Furniture2);

        when(furnitureService.getTop10Furnitures()).thenReturn(furnitures);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(furnitureController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(furnitures);

        // Act & Assert
        mockMvc.perform(get("/furniture/statistics/top10"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testGetFurniture() throws Exception {
        // Arrange
        Furniture furniture = new Furniture();

        when(furnitureService.getFurnitureById(1L)).thenReturn(CompletableFuture.completedFuture(furniture));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(furnitureController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(furniture);

        // Act & Assert
        mockMvc.perform(get("/furniture/get/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testRegisterFurniture() throws Exception {
        // Arrange
        Furniture furniture = new Furniture();

        when(furnitureService.addFurniture(any(Furniture.class))).thenReturn(furniture);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(furnitureController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(furniture);

        // Act & Assert
        mockMvc.perform(post("/furniture/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testUpdateFurniture() throws Exception {
        // Arrange
        Furniture furniture = new Furniture();

        // Simulate updating the furniture object
        when(furnitureService.updateFurniture(eq(1L), any(Furniture.class))).thenReturn(furniture);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(furnitureController).build();

        // Convert the expected result to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(furniture);

        // Act & Assert
        mockMvc.perform(put("/furniture/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testDeleteFurniture() throws Exception {
        Long furnitureId = 1L;
        Furniture furniture = new Furniture();
        furniture.setInternalId(furnitureId);

        when(furnitureService.deleteFurniture(furnitureId)).thenReturn(furniture);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(furnitureController).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(furniture);

        mockMvc.perform(delete("/furniture/delete/" + furnitureId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}