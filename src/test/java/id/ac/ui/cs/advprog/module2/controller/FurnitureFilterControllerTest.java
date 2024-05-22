package id.ac.ui.cs.advprog.module2.controller;


import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import id.ac.ui.cs.advprog.module2.model.FurniturePage;
import id.ac.ui.cs.advprog.module2.service.FurnitureFilterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(FurnitureFilterController.class)
public class FurnitureFilterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FurnitureFilterService furnitureFilterService;

    @Mock
    FurnitureFilter furnitureFilter;

    @Mock
    FurniturePage furniturePage;

    @InjectMocks
    private FurnitureFilterController furnitureFilterController;

    @Test
    void getFurnituresTest() throws Exception {
        Furniture furniture = new Furniture();

        Furniture furniture2 = new Furniture();

        Furniture furniture3 = new Furniture();


        // page content
        List<Furniture> listOfFurniture = new ArrayList<>();
        listOfFurniture.add(furniture);
        listOfFurniture.add(furniture2);
        listOfFurniture.add(furniture3);

        // pageable
        Pageable pageable = PageRequest.of(0, 32);

        when(furnitureFilterService.getFurnitures(furnitureFilter, furniturePage))
                .thenReturn(new PageImpl<>(listOfFurniture, pageable, 3));

        ResponseEntity<Page<Furniture>> responseEntity = furnitureFilterController.getFurnitures(furnitureFilter, furniturePage);

        assertEquals("200 OK", responseEntity.getStatusCode().toString());
        assertEquals(3, responseEntity.getBody().getContent().size());
    }


}
