package com.inventatrack.platform.inventory.interfaces.rest.transform;

import com.inventatrack.platform.inventory.domain.model.aggregates.Inventory;
import com.inventatrack.platform.inventory.interfaces.rest.resources.InventoryResource;
import org.springframework.stereotype.Component;

@Component
public class InventoryResourceFromEntityAssembler {

    public static InventoryResource toResource(Inventory inventory) {
        return new InventoryResource(
                inventory.getId(),
                inventory.getMonth(),
                inventory.getProducts()
        );
    }
}
