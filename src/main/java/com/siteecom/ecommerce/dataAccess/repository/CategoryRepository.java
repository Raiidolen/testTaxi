package com.siteecom.ecommerce.dataAccess.repository;

import com.siteecom.ecommerce.dataAccess.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // Pas de méthode spécifique pour l'instant.
    // findAll() est déjà inclus par héritage.
}