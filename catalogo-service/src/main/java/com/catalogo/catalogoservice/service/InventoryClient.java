package com.catalogo.catalogoservice.service;

import com.catalogo.catalogoservice.dto.InventarioDTO;
import com.catalogo.catalogoservice.exception.InventarioServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryClient {

    private final RestClient InventarioRestClient;

    /**
     * Consulta el inventario de un item.
     * Si el servicio no responde o hay error, devuelve un fallback con stock=0 y status=UNKNOWN.
     */
    public InventarioDTO obtenerInventario(Integer itemId) {
        try {
            log.debug("Consultando Inventario-service para itemId={}", itemId);

            InventarioDTO response = InventarioRestClient.get()
                    .uri("/api/Inventario/{itemId}", itemId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        throw new InventarioServiceException(
                                "Inventario no encontrado para itemId: " + itemId);
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                        throw new InventarioServiceException(
                                "Error en Inventario-service para itemId: " + itemId);
                    })
                    .body(InventarioDTO.class);

            log.debug("Respuesta de Inventario-service: {}", response);
            return response;

        } catch (ResourceAccessException ex) {
            // Timeout, conexión rechazada, DNS no resuelve...
            log.warn("Inventario-service no responde para itemId={}: {}", itemId, ex.getMessage());
            return fallback(itemId);

        } catch (InventarioServiceException ex) {
            // El servicio respondió con 4xx o 5xx
            log.warn("Error al consultar Inventario-service para itemId={}: {}", itemId, ex.getMessage());
            return fallback(itemId);

        } catch (Exception ex) {
            // Cualquier otro error inesperado
            log.error("Error inesperado al consultar Inventario-service para itemId={}", itemId, ex);
            return fallback(itemId);
        }
    }

    private InventarioDTO fallback(Integer itemId) {
        return InventarioDTO.builder()
                .itemId(itemId)
                .stock(0)
                .status("UNKNOWN")
                .build();
    }
}
