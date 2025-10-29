package com.inventatrack.platform.notifications.domain.model;

import java.time.LocalDate;

public record ProductExpiryNotification(
        Long productId,
        String productName,
        LocalDate expiryDate,
        String message
) {}
