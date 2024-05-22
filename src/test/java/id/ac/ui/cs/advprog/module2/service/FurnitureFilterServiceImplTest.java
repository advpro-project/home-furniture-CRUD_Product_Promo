package id.ac.ui.cs.advprog.module2.service;


import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import id.ac.ui.cs.advprog.module2.model.FurniturePage;
import id.ac.ui.cs.advprog.module2.repository.FurnitureFilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FurnitureFilterServiceImplTest {

    @Mock
    FurnitureFilter furnitureFilter;

    @Mock
    FurniturePage furniturePage;
    @Mock
    private FurnitureFilterRepository furnitureFilterRepository;

    @InjectMocks
    private FurnitureFilterServiceImpl furnitureFilterServiceImpl;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getFurnituresTest() {
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

        when(furnitureFilterRepository.findAllWithFilter(furnitureFilter, furniturePage))
                .thenReturn(new PageImpl<>(listOfFurniture, pageable, 3));

        Page<Furniture> page = furnitureFilterServiceImpl.getFurnitures(furnitureFilter, furniturePage);

        assertEquals( 1, page.getTotalPages());
        assertEquals( 3, page.getTotalElements());
        assertEquals( 32, page.getSize());
        assertEquals( 0, page.getNumber());
        assertFalse(page.getContent().isEmpty());
    }
}
