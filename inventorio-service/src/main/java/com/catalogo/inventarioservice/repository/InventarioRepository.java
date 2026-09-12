package com.catalogo.inventarioservice.repository;

import com.catalogo.inventarioservice.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    Optional<Inventario> findByItemId(Integer itemId);

    List<Inventario> findByStatus(String status);

    List<Inventario> findByStockLessThanEqual(Integer stock);
}
