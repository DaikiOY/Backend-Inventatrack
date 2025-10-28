package com.inventatrack.platform.inventory.domain.model.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private Long categoryId;
    private String name;
    private Integer quantity;
    private String expirationDate;
}
