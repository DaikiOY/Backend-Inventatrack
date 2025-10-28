package com.inventatrack.platform.reports.interfaces.rest;

import com.inventatrack.platform.inventory.domain.model.entities.Product;
import com.inventatrack.platform.reports.application.internal.queryservices.ReportQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportQueryService reportQueryService;

    public ReportController(ReportQueryService reportQueryService) {
        this.reportQueryService = reportQueryService;
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts(@RequestParam(defaultValue = "10") int threshold) {
        List<Product> products = reportQueryService.findLowStockProducts(threshold);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/expiring-products")
    public ResponseEntity<List<Product>> getExpiringProducts(@RequestParam(defaultValue = "7") int days) {
        List<Product> products = reportQueryService.findExpiringProducts(days);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/expiring-in-14-days")
    public ResponseEntity<List<Product>> getProductsExpiringIn14Days() {
        List<Product> products = reportQueryService.findProductsExpiringIn14Days();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/expired-products")
    public ResponseEntity<List<Product>> getExpiredProducts() {
        List<Product> products = reportQueryService.findExpiredProducts();
        return ResponseEntity.ok(products);
    }
}
