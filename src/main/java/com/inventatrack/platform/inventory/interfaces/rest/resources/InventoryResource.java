package com.inventatrack.platform.inventory.interfaces.rest.resources;

import com.inventatrack.platform.inventory.domain.model.entities.Product;
import java.util.List;

public record InventoryResource(
        Long id,
        String month,
        List<Product> products
) {}
