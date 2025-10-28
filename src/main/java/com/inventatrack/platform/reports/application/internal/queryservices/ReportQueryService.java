package com.inventatrack.platform.reports.application.internal.queryservices;

import com.inventatrack.platform.inventory.domain.model.entities.Product;
import java.util.List;

public interface ReportQueryService {
    List<Product> findLowStockProducts(int threshold);
    List<Product> findExpiringProducts(int days);
    List<Product> findProductsExpiringIn14Days();
    List<Product> findExpiredProducts();
}
