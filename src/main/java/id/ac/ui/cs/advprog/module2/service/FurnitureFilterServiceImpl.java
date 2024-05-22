package id.ac.ui.cs.advprog.module2.service;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import id.ac.ui.cs.advprog.module2.model.FurniturePage;
import id.ac.ui.cs.advprog.module2.repository.FurnitureFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FurnitureFilterServiceImpl implements FurnitureFilterService {

    @Autowired
    private FurnitureFilterRepository furnitureFilterRepository;

    public Page<Furniture> getFurnitures(FurnitureFilter furnitureFilter, FurniturePage furniturePage) {
        return furnitureFilterRepository.findAllWithFilter(furnitureFilter, furniturePage);
    }
}
