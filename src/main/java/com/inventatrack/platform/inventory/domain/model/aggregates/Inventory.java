package com.inventatrack.platform.inventory.domain.model.aggregates;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;
import com.inventatrack.platform.inventory.domain.model.entities.Product;
import com.inventatrack.platform.inventory.domain.model.commands.CreateInventoryCommand;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;

    @ElementCollection
    @CollectionTable(name = "inventory_products", joinColumns = @JoinColumn(name = "inventory_id"))
    private List<Product> products;

    public Inventory() {}

    public Inventory(CreateInventoryCommand command) {
        this.month = command.getMonth();
        this.products = command.getProducts();
    }
}
