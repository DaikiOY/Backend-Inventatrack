package com.inventatrack.platform.inventory.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inventatrack.platform.inventory.domain.model.entities.Product;
import com.inventatrack.platform.inventory.domain.model.aggregates.Inventory;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByMonth(String month);

    @Query("SELECT p FROM Inventory i JOIN i.products p WHERE p.categoryId = :categoryId")
    List<Product> findProductsByCategoryId(Long categoryId);

    @Query("SELECT i FROM Inventory i JOIN i.products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Inventory> findByProductNameContaining(String name);

    @Query("SELECT p FROM Inventory i JOIN i.products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<com.inventatrack.platform.inventory.domain.model.entities.Product> findProductsByNameContaining(String name);

}


