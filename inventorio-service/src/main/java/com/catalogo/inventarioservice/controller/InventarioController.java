package com.catalogo.inventarioservice.controller;


import com.catalogo.inventarioservice.dto.InventarioDTO;
import com.catalogo.inventarioservice.dto.InventarioRequest;
import com.catalogo.inventarioservice.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService InventarioService;

    /**
     * Endpoint principal consumido por catalog-service.
     */
    @GetMapping("/{itemId}")
    public ResponseEntity<InventarioDTO> obtenerPorItemId(@PathVariable Integer itemId) {
        return ResponseEntity.ok(InventarioService.obtenerPorItemId(itemId));
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listarTodos() {
        return ResponseEntity.ok(InventarioService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> crear(@Valid @RequestBody InventarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(InventarioService.crear(request));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Integer itemId,
                                                   @Valid @RequestBody InventarioRequest request) {
        return ResponseEntity.ok(InventarioService.actualizar(itemId, request));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer itemId) {
        InventarioService.eliminar(itemId);
        return ResponseEntity.noContent().build();
    }
}