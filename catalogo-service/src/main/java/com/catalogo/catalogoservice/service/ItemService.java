package com.catalogo.catalogoservice.service;

import com.catalogo.catalogoservice.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> listarTodos();

    ItemDTO obtenerPorId(Integer id);
}
