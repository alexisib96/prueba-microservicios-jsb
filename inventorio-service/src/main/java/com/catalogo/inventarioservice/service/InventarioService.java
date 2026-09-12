package com.catalogo.inventarioservice.service;
import com.catalogo.inventarioservice.dto.InventarioDTO;
import com.catalogo.inventarioservice.dto.InventarioRequest;

import java.util.List;

public interface InventarioService {

    InventarioDTO obtenerPorItemId(Integer itemId);

    List<InventarioDTO> listarTodos();

    InventarioDTO crear(InventarioRequest request);

    InventarioDTO actualizar(Integer itemId, InventarioRequest request);

    void eliminar(Integer itemId);
}
