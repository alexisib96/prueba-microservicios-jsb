package com.catalogo.catalogoservice.service.impl;

import com.catalogo.catalogoservice.dto.InventarioDTO;
import com.catalogo.catalogoservice.dto.ItemDTO;
import com.catalogo.catalogoservice.entity.Item;
import com.catalogo.catalogoservice.exception.ResourceNotFoundException;
import com.catalogo.catalogoservice.repository.ItemRepository;
import com.catalogo.catalogoservice.service.InventoryClient;
import com.catalogo.catalogoservice.service.ItemService;
import com.catalogo.catalogoservice.service.ScoreCalculator;
import com.catalogo.catalogoservice.util.DataNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private static final String TITULO_POR_DEFECTO = "Sin título";
    private static final String IMAGEN_POR_DEFECTO =
            "https://via.placeholder.com/400x300?text=Sin+Imagen";

    private final ItemRepository itemRepository;
    private final InventoryClient inventoryClient;
    private final ScoreCalculator scoreCalculator;

    @Override
    @Transactional(readOnly = true)
    public List<ItemDTO> listarTodos() {
        return itemRepository.findAll().stream()
                .map(this::construirItemDTO)
                .sorted(Comparator.comparingDouble(ItemDTO::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDTO obtenerPorId(Integer id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item no encontrado con id: " + id));
        return construirItemDTO(item);
    }

    /**
     * Construye el DTO completo de un item:
     * 1. Consulta el inventario (con fallback si falla).
     * 2. Normaliza los datos del item.
     * 3. Calcula el score.
     */
    private ItemDTO construirItemDTO(Item item) {
        // 1. Inventario (con fallback interno si inventory-service falla)
        InventarioDTO inv = inventoryClient.obtenerInventario(item.getId());

        // 2. Normalización
        String titulo = DataNormalizer.normalizarTexto(item.getTitle(), TITULO_POR_DEFECTO);
        String imagen = DataNormalizer.normalizarTexto(item.getImageUrl(), IMAGEN_POR_DEFECTO);

        // 3. Score
        double score = scoreCalculator.calcular(
                item.getRating(), inv.getStock(), item.getPrice());

        return ItemDTO.builder()
                .id(item.getId())
                .title(titulo)
                .description(item.getDescription())
                .imageUrl(imagen)
                .price(item.getPrice())
                .rating(item.getRating())
                .stock(inv.getStock())
                .status(inv.getStatus())
                .score(score)
                .build();
    }
}
