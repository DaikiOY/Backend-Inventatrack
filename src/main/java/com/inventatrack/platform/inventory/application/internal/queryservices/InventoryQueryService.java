package com.inventatrack.platform.inventory.application.internal.queryservices;

import com.inventatrack.platform.inventory.domain.model.aggregates.Inventory;
import java.util.List;
import java.util.Optional;
import com.inventatrack.platform.inventory.domain.model.entities.Product;

public interface InventoryQueryService {
    List<Inventory> findAll();
    List<Inventory> findByMonth(String month);
    Optional<Inventory> findById(Long id);
    List<Product> findProductsByCategoryId(Long categoryId);
    List<Inventory> findByProductName(String name);
    List<Product> findProductsByName(String name);

}
