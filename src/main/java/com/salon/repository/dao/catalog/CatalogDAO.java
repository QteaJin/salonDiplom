package com.salon.repository.dao.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.repository.entity.catalog.Catalog;

public interface CatalogDAO extends JpaRepository<Catalog, Long>{

}
