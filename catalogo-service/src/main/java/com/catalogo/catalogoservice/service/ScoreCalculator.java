package com.catalogo.catalogoservice.service;

import com.catalogo.catalogoservice.util.DataNormalizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
@Slf4j
public class ScoreCalculator {

    private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);

    /**
     * Calcula el score de un item.
     * Fórmula: (rating × ln(stock + 1)) / max(price, 1)
     */
    public double calcular(BigDecimal rating, Integer stock, BigDecimal price) {
        BigDecimal ratingNorm = DataNormalizer.normalizarRating(rating);
        BigDecimal priceNorm = DataNormalizer.normalizarPrecio(price);
        int stockNorm = DataNormalizer.normalizarStock(stock);

        // ln(stock + 1)
        double lnStock = Math.log(stockNorm + 1);

        // rating × ln(stock + 1)
        BigDecimal numerador = ratingNorm.multiply(BigDecimal.valueOf(lnStock), MC);

        // max(price, 1) ya garantizado por normalizarPrecio
        BigDecimal resultado = numerador.divide(priceNorm, MC);

        double score = resultado.doubleValue();
        log.trace("Score calculado: rating={}, stock={}, price={} → score={}",
                ratingNorm, stockNorm, priceNorm, score);

        return score;
    }
}
