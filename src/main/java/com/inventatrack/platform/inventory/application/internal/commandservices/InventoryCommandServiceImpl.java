package com.inventatrack.platform.inventory.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.inventatrack.platform.inventory.domain.model.commands.CreateInventoryCommand;
import com.inventatrack.platform.inventory.domain.model.aggregates.Inventory;
import com.inventatrack.platform.inventory.infrastructure.persistence.jpa.repository.InventoryRepository;
import com.inventatrack.platform.inventory.domain.exceptions.InventoryNotFoundException;

@Service
@Transactional
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final InventoryRepository inventoryRepository;

    public InventoryCommandServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory handle(CreateInventoryCommand command) {
        Inventory inventory = new Inventory(command);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id, CreateInventoryCommand command) {
        return inventoryRepository.findById(id).map(existingInventory -> {
            existingInventory.setMonth(command.getMonth());
            existingInventory.setProducts(command.getProducts());
            return inventoryRepository.save(existingInventory);
        }).orElseThrow(() -> new InventoryNotFoundException("Inventory not found with id " + id));
    }

    @Override
    public boolean deleteById(Long id) {
        if (!inventoryRepository.existsById(id)) {
            return false;
        }
        inventoryRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteProductFromInventory(Long inventoryId, Long productId) {
        return inventoryRepository.findById(inventoryId).map(inventory -> {
            boolean removed = inventory.getProducts().removeIf(p -> p.getId().equals(productId));
            if (removed) {
                inventoryRepository.save(inventory);
                return true;
            }
            return false;
        }).orElse(false);
    }
}
