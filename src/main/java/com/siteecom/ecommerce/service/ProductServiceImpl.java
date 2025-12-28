package com.siteecom.ecommerce.service;

import com.siteecom.ecommerce.dataAccess.entity.CategoryEntity;
import com.siteecom.ecommerce.dataAccess.entity.ProductEntity;
import com.siteecom.ecommerce.dataAccess.repository.CategoryRepository;
import com.siteecom.ecommerce.dataAccess.repository.ProductRepository;
import com.siteecom.ecommerce.dataAccess.util.ProviderConverter;
import com.siteecom.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProviderConverter converter;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Product> getAllActiveProducts() {
        List<ProductEntity> entities = productRepository.findByActiveTrue();

        // On transforme la liste d'Entités en liste de Modèles (DTO)
        return entities.stream()
                .map(entity -> converter.productEntityToProductModel(entity))
                .collect(Collectors.toList());
    }

    public Product getProductById(Integer id) {
        // On cherche en BDD, sinon on lance une erreur (ou null)
        return productRepository.findById(Long.valueOf(id))
                .map(entity -> converter.productEntityToProductModel(entity))
                .orElse(null);
    }

    public Page<Product> searchProducts(Integer categoryId, String keyword, int page, int size) {
        // On crée un objet Pageable (Page 0, 1, 2... de taille 'size')
        // On peut ajouter un tri par défaut (ici par ID)
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        // Appel au repository
        Page<ProductEntity> entitiesPage = productRepository.searchProducts(categoryId, keyword, pageable);

        // Conversion magique : Page<Entity> -> Page<Model>
        return entitiesPage.map(entity -> converter.productEntityToProductModel(entity));
    }
}