package com.siteecom.ecommerce.dataAccess.repository;

import com.siteecom.ecommerce.dataAccess.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Récupérer tous les produits d'une catégorie spécifique
    List<ProductEntity> findByCategoryId(Long categoryId);

    // Retrouver un produit par son code unique (SKU)
    Optional<ProductEntity> findBySku(String sku);

    // Rechercher des produits contenant un mot clé dans le nom (FR ou EN)
    // IgnoreCase permet de trouver "Dell" même si on cherche "dell"
    List<ProductEntity> findByNameFrContainingIgnoreCaseOrNameEnContainingIgnoreCase(String keywordFr, String keywordEn);

    // Pour n'afficher que les produits actifs
    List<ProductEntity> findByActiveTrue();

    @Query("SELECT p FROM ProductEntity p WHERE p.active = true " +
            "AND (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "AND (:keyword IS NULL OR LOWER(p.nameFr) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.descriptionFr) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<ProductEntity> searchProducts(@Param("categoryId") Integer categoryId,
                                       @Param("keyword") String keyword,
                                       Pageable pageable);
}