package com.catalogo.catalogoservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ItemDTO {
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private BigDecimal rating;

    // Datos del inventory-service
    private Integer stock;
    private String status;

    // Score calculado
    private Double score;
}
