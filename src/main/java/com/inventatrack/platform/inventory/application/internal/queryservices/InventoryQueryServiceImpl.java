package com.inventatrack.platform.inventory.application.internal.queryservices;

import org.springframework.stereotype.Service;
import com.inventatrack.platform.inventory.domain.model.aggregates.Inventory;
import com.inventatrack.platform.inventory.infrastructure.persistence.jpa.repository.InventoryRepository;
import java.util.List;
import java.util.Optional;
import com.inventatrack.platform.inventory.domain.model.entities.Product;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {

    private final InventoryRepository inventoryRepository;

    public InventoryQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findByMonth(String month) {
        return inventoryRepository.findByMonth(month);
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return inventoryRepository.findProductsByCategoryId(categoryId);
    }

    @Override
    public List<Inventory> findByProductName(String name) {
        return inventoryRepository.findByProductNameContaining(name);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return inventoryRepository.findProductsByNameContaining(name);
    }

}
