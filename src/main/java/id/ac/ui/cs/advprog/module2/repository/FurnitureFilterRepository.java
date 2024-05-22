package id.ac.ui.cs.advprog.module2.repository;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import id.ac.ui.cs.advprog.module2.model.FurniturePage;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.Predicate;

import java.util.List;

@Repository
public class FurnitureFilterRepository {

    @Autowired
    FurnitureFilterDao furnitureFilterDao;
    public Page<Furniture> findAllWithFilter(FurnitureFilter furnitureFilter, FurniturePage furniturePage) {
        TypedQuery<Furniture> sqlQuery = furnitureFilterDao.buildFurnitureFilterQuery(furnitureFilter);
        sqlQuery.setFirstResult(furniturePage.getPageNumber() * furniturePage.getPageSize());
        sqlQuery.setMaxResults(furniturePage.getPageSize());

        Pageable pageable = PageRequest.of(furniturePage.getPageNumber(), furniturePage.getPageSize());

        long furnituresCount = furnitureFilterDao.buildCountFurnitureQuery(furnitureFilter);

        PageImpl<Furniture> pages = new PageImpl<>(sqlQuery.getResultList(), pageable, furnituresCount);
        return pages;
    }
}
