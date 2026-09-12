package com.catalogo.catalogoservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Items")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Titulo", nullable = false, length = 200)
    private String title;

    @Column(name = "Descripcion", length = 1000)
    private String description;

    @Column(name = "ImagenUrl", length = 500)
    private String imageUrl;

    @Column(name = "Precio", precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "Rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}
