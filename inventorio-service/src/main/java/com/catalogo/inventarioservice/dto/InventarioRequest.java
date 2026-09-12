package com.catalogo.inventarioservice.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class InventarioRequest {

    @NotNull(message = "El itemId es obligatorio")
    private Integer itemId;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotBlank(message = "El status es obligatorio")
    @Pattern(regexp = "AVAILABLE|LOW_STOCK|OUT_OF_STOCK|DISCONTINUED",
            message = "Status inválido")
    private String status;
}
