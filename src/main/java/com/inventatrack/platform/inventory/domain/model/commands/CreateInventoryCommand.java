package com.inventatrack.platform.inventory.domain.model.commands;

import com.inventatrack.platform.inventory.domain.model.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateInventoryCommand {
    private String month;
    private List<Product> products;
}

