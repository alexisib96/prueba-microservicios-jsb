package com.catalogo.catalogoservice.repository;


import com.catalogo.catalogoservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByTitleContainingIgnoreCase(String title);
}
