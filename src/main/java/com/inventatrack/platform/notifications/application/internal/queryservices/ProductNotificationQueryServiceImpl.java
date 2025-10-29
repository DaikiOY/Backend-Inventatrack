package com.inventatrack.platform.notifications.application.internal.queryservices;

import com.inventatrack.platform.inventory.domain.model.entities.Product;
import com.inventatrack.platform.inventory.infrastructure.persistence.jpa.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductNotificationQueryServiceImpl implements ProductNotificationQueryService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final InventoryRepository inventoryRepository;

    public ProductNotificationQueryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Product> findProductsExpiringToday() {
        LocalDate today = LocalDate.now();

        return getAllProducts().filter(product -> {
            try {
                LocalDate expiration = LocalDate.parse(product.getExpirationDate(), DATE_FORMATTER);
                return expiration.isEqual(today);
            } catch (Exception e) {
                return false;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductsExpiringThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate endOfWeek = today.plusDays(7);

        return getAllProducts().filter(product -> {
            try {
                LocalDate expiration = LocalDate.parse(product.getExpirationDate(), DATE_FORMATTER);
                return !expiration.isBefore(today) && !expiration.isAfter(endOfWeek);
            } catch (Exception e) {
                return false;
            }
        }).collect(Collectors.toList());
    }

    private Stream<Product> getAllProducts() {
        return inventoryRepository.findAll().stream()
                .flatMap(inventory -> inventory.getProducts().stream());
    }

    @Override
    public List<Product> findProductsLowStock(int threshold) {
        return getAllProducts()
                .filter(product -> product.getQuantity() != null && product.getQuantity() < threshold)
                .collect(Collectors.toList());
    }

}
