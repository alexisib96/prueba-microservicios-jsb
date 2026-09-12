package com.catalogo.catalogoservice.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public final class DataNormalizer {

    private DataNormalizer() {
        // Clase utilitaria: no instanciable
    }

    /**
     * Normaliza un texto: null o vacío → valor por defecto.
     */
    public static String normalizarTexto(String valor, String valorPorDefecto) {
        if (valor == null || valor.trim().isEmpty()) {
            return valorPorDefecto;
        }
        return valor.trim();
    }

    /**
     * Normaliza un rating: null, inválido, negativo o > 5 → 0.
     */
    public static BigDecimal normalizarRating(BigDecimal rating) {
        if (rating == null) return BigDecimal.ZERO;
        if (rating.compareTo(BigDecimal.ZERO) < 0) return BigDecimal.ZERO;
        if (rating.compareTo(new BigDecimal("5")) > 0) return new BigDecimal("5");
        return rating;
    }

    /**
     * Normaliza un precio: null, inválido o <= 0 → 1 (por el max(price, 1) de la fórmula).
     */
    public static BigDecimal normalizarPrecio(BigDecimal precio) {
        if (precio == null) return BigDecimal.ONE;
        if (precio.compareTo(BigDecimal.ZERO) <= 0) return BigDecimal.ONE;
        return precio;
    }

    /**
     * Normaliza un stock: null o negativo → 0.
     */
    public static int normalizarStock(Integer stock) {
        if (stock == null) return 0;
        if (stock < 0) return 0;
        return stock;
    }
}
