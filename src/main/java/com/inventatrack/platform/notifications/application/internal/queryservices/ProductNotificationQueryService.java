package com.inventatrack.platform.notifications.application.internal.queryservices;

import com.inventatrack.platform.inventory.domain.model.entities.Product;

import java.util.List;

public interface ProductNotificationQueryService {
    List<Product> findProductsExpiringToday();
    List<Product> findProductsExpiringThisWeek();
    List<Product> findProductsLowStock(int threshold);
}
