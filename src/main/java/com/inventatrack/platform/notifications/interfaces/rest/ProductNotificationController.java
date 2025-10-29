package com.inventatrack.platform.notifications.interfaces.rest;

import com.inventatrack.platform.inventory.domain.model.entities.Product;
import com.inventatrack.platform.notifications.application.internal.queryservices.ProductNotificationQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class ProductNotificationController {

    private final ProductNotificationQueryService notificationQueryService;

    public ProductNotificationController(ProductNotificationQueryService notificationQueryService) {
        this.notificationQueryService = notificationQueryService;
    }

    @GetMapping("/expiring-today")
    public ResponseEntity<List<Product>> getProductsExpiringToday() {
        List<Product> products = notificationQueryService.findProductsExpiringToday();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/this-week")
    public ResponseEntity<List<Product>> getProductsExpiringThisWeek() {
        List<Product> products = notificationQueryService.findProductsExpiringThisWeek();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts(@RequestParam(defaultValue = "10") int threshold) {
        List<Product> products = notificationQueryService.findProductsLowStock(threshold);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
}

