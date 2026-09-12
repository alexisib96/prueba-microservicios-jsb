package com.catalogo.inventarioservice.service.impl;

import com.catalogo.inventarioservice.dto.InventarioDTO;
import com.catalogo.inventarioservice.dto.InventarioRequest;
import com.catalogo.inventarioservice.entity.Inventario;
import com.catalogo.inventarioservice.exception.ResourceNotFoundException;
import com.catalogo.inventarioservice.repository.InventarioRepository;
import com.catalogo.inventarioservice.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository InventarioRepository;

    private InventarioDTO toDTO(Inventario inv) {
        return InventarioDTO.builder()
                .itemId(inv.getItemId())
                .stock(inv.getStock())
                .status(inv.getStatus())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioDTO obtenerPorItemId(Integer itemId) {
        Inventario inv = InventarioRepository.findByItemId(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventario no encontrado para itemId: " + itemId));
        return toDTO(inv);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioDTO> listarTodos() {
        return InventarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InventarioDTO crear(InventarioRequest request) {
        if (InventarioRepository.findByItemId(request.getItemId()).isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe inventario para itemId: " + request.getItemId());
        }

        Inventario inv = Inventario.builder()
                .itemId(request.getItemId())
                .stock(request.getStock())
                .status(request.getStatus())
                .build();

        return toDTO(InventarioRepository.save(inv));
    }

    @Override
    @Transactional
    public InventarioDTO actualizar(Integer itemId, InventarioRequest request) {
        Inventario inv = InventarioRepository.findByItemId(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventario no encontrado para itemId: " + itemId));

        inv.setStock(request.getStock());
        inv.setStatus(request.getStatus());

        return toDTO(InventarioRepository.save(inv));
    }

    @Override
    @Transactional
    public void eliminar(Integer itemId) {
        Inventario inv = InventarioRepository.findByItemId(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventario no encontrado para itemId: " + itemId));
        InventarioRepository.delete(inv);
    }
}
