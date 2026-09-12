package com.catalogo.inventarioservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Inventorio")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "ItemId", nullable = false, unique = true)
    private Integer itemId;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    @Column(name = "Status", nullable = false, length = 30)
    private String status;

    @Column(name = "UpdatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.stock == null) this.stock = 0;
        if (this.status == null) this.status = "AVAILABLE";
    }
}
