package com.inventatrack.platform.reports.application.internal.queryservices;

import com.inventatrack.platform.inventory.domain.model.entities.Product;
import com.inventatrack.platform.inventory.infrastructure.persistence.jpa.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    private final InventoryRepository inventoryRepository;

    public ReportQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Product> findLowStockProducts(int threshold) {
        return inventoryRepository.findAll().stream()
                .flatMap(inventory -> inventory.getProducts().stream())
                .filter(product -> product.getQuantity() != null && product.getQuantity() < threshold)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findExpiringProducts(int days) {
        LocalDate today = LocalDate.now();
        LocalDate limitDate = today.plusDays(days);

        return inventoryRepository.findAll().stream()
                .flatMap(inventory -> inventory.getProducts().stream())
                .filter(product -> {
                    try {
                        LocalDate expiration = LocalDate.parse(product.getExpirationDate());
                        return !expiration.isBefore(today) && !expiration.isAfter(limitDate);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsExpiringIn14Days() {
        LocalDate today = LocalDate.now();
        LocalDate limitDate = today.plusDays(14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return inventoryRepository.findAll().stream()
                .flatMap(inventory -> inventory.getProducts().stream())
                .filter(product -> {
                    try {
                        LocalDate expiration = LocalDate.parse(product.getExpirationDate(), formatter);
                        return !expiration.isBefore(today) && !expiration.isAfter(limitDate);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findExpiredProducts() {
        LocalDate today = LocalDate.now();

        return inventoryRepository.findAll().stream()
                .flatMap(inventory -> inventory.getProducts().stream())
                .filter(product -> {
                    try {
                        LocalDate expiration = LocalDate.parse(product.getExpirationDate());
                        return expiration.isBefore(today);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
