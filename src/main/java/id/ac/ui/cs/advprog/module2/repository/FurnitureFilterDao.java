package id.ac.ui.cs.advprog.module2.repository;

import id.ac.ui.cs.advprog.module2.model.Furniture;
import id.ac.ui.cs.advprog.module2.model.FurnitureFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FurnitureFilterDao {

    @Autowired
    private EntityManager entityManager;

    public TypedQuery<Furniture> buildFurnitureFilterQuery(FurnitureFilter furnitureFilter)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Furniture> criteriaQuery = criteriaBuilder.createQuery(Furniture.class);

        //select * from furnitures
        Root<Furniture> root = criteriaQuery.from(Furniture.class);

        //WHERE clauses
        List<Predicate> predicates = buildPredicate(furnitureFilter, root);
        if (!predicates.isEmpty()) {
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }

        // ORDER BY clauses
        if (furnitureFilter.getPriceOrder() != null) {
            if (furnitureFilter.getPriceOrder().equalsIgnoreCase("max")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("discountedPrice")));
            }

            else if (furnitureFilter.getPriceOrder().equalsIgnoreCase("min")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("discountedPrice")));
            }
        }


        TypedQuery<Furniture> query = entityManager.createQuery(criteriaQuery);

        return query;
    }

    public List<Predicate> buildPredicate(FurnitureFilter furnitureFilter, Root<Furniture> furnitureRoot) {
        CriteriaBuilder predicateBuilder = entityManager.getCriteriaBuilder();

        List<Predicate> predicates = new ArrayList<>();
        if (furnitureFilter.getName() != null) {
            Predicate searchKeywordNamePredicate = predicateBuilder
                    .like(furnitureRoot.get("name"), "%" + furnitureFilter.getName() + "%");
            predicates.add(searchKeywordNamePredicate);
        }

        if (furnitureFilter.getType() != null) {
            Predicate typeFilterPredicate = predicateBuilder
                    .equal(furnitureRoot.get("type"), furnitureFilter.getType());
            predicates.add(typeFilterPredicate);
        }

        if (furnitureFilter.isDiscount()) {
            Predicate isDiscountPredicate = predicateBuilder
                    .equal(furnitureRoot.get("hasDiscount"), furnitureFilter.isDiscount());
            predicates.add(isDiscountPredicate);
        }

        return predicates;
    }

    public long buildCountFurnitureQuery(FurnitureFilter furnitureFilter) {
        CriteriaBuilder countBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = countBuilder.createQuery(Long.class);

        //select * from furnitures
        Root<Furniture> root = countQuery.from(Furniture.class);

        // count aggregate function
        List<Predicate> predicates = buildPredicate(furnitureFilter, root);
        if (!predicates.isEmpty()) {
            countQuery.select(countBuilder.count(root))
                    .where(countBuilder.and(predicates.toArray(new Predicate[0])));
        }
        else {
            countQuery.select(countBuilder.count(root));
        }

        TypedQuery<Long> count = entityManager.createQuery(countQuery);

        return count.getSingleResult();
    }
}
