package com.inventatrack.platform.inventory.interfaces.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;
import com.inventatrack.platform.inventory.application.internal.commandservices.InventoryCommandService;
import com.inventatrack.platform.inventory.application.internal.queryservices.InventoryQueryService;
import com.inventatrack.platform.inventory.domain.model.aggregates.Inventory;
import com.inventatrack.platform.inventory.interfaces.rest.resources.*;
import com.inventatrack.platform.inventory.interfaces.rest.transform.*;
import com.inventatrack.platform.inventory.domain.model.entities.Product;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryCommandService inventoryCommandService;
    private final InventoryQueryService inventoryQueryService;

    public InventoryController(
            InventoryCommandService inventoryCommandService,
            InventoryQueryService inventoryQueryService) {
        this.inventoryCommandService = inventoryCommandService;
        this.inventoryQueryService = inventoryQueryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResource> createInventory(@RequestBody CreateInventoryResource resource) {
        Inventory inventory = inventoryCommandService.handle(
                CreateInventoryCommandFromResourceAssembler.toCommand(resource)
        );
        return ResponseEntity.ok(
                InventoryResourceFromEntityAssembler.toResource(inventory)
        );
    }

    @GetMapping
    public ResponseEntity<List<InventoryResource>> getAllInventories() {
        List<Inventory> inventories = inventoryQueryService.findAll();
        List<InventoryResource> resources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<InventoryResource>> getInventoriesByMonth(@PathVariable String month) {
        List<Inventory> inventories = inventoryQueryService.findByMonth(month);
        List<InventoryResource> resources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResource> getInventoryById(@PathVariable Long id) {
        return inventoryQueryService.findById(id)
                .map(inventory -> ResponseEntity.ok(InventoryResourceFromEntityAssembler.toResource(inventory)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = inventoryQueryService.findProductsByCategoryId(categoryId);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResource> updateInventory(
            @PathVariable Long id,
            @RequestBody CreateInventoryResource resource) {

        Inventory updatedInventory = inventoryCommandService.updateInventory(
                id, CreateInventoryCommandFromResourceAssembler.toCommand(resource)
        );

        return ResponseEntity.ok(
                InventoryResourceFromEntityAssembler.toResource(updatedInventory)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        boolean deleted = inventoryCommandService.deleteById(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Inventario con ID " + id + " no encontrado.");
        }

        return ResponseEntity.ok("Inventario eliminado exitosamente.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<InventoryResource>> searchInventoriesByProductName(@RequestParam String name) {
        List<Inventory> inventories = inventoryQueryService.findByProductName(name);
        List<InventoryResource> resources = inventories.stream()
                .map(InventoryResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = inventoryQueryService.findProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{inventoryId}/products/{productId}")
    public ResponseEntity<String> deleteProductFromInventory(
            @PathVariable Long inventoryId,
            @PathVariable Long productId) {

        boolean deleted = inventoryCommandService.deleteProductFromInventory(inventoryId, productId);

        if (deleted) {
            return ResponseEntity.ok("Producto con ID " + productId + " eliminado del inventario " + inventoryId + ".");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
