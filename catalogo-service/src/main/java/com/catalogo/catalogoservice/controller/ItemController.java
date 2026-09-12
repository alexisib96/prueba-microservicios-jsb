package com.catalogo.catalogoservice.controller;

import com.catalogo.catalogoservice.dto.ItemDTO;
import com.catalogo.catalogoservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> listar() {
        return ResponseEntity.ok(itemService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(itemService.obtenerPorId(id));
    }
}
