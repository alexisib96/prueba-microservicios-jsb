package com.catalogo.inventarioservice.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class InventarioDTO {
    private Integer itemId;
    private Integer stock;
    private String status;
}
