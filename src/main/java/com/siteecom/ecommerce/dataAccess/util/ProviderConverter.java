package com.siteecom.ecommerce.dataAccess.util;

import com.siteecom.ecommerce.dataAccess.entity.ProductEntity;
import com.siteecom.ecommerce.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProviderConverter {

    public Product productEntityToProductModel(ProductEntity entity) {
        Product model = new Product();
        // Mapping manuel ou via Dozer, mais ici on gère la langue
        // Pour l'instant on force le FR, plus tard on passera la locale en paramètre
        model.setName(entity.getNameFr());
        model.setDescription(entity.getDescriptionFr());
        model.setPrice(entity.getPrice().doubleValue()); // Conversion BigDecimal -> Double pour l'affichage simple
        model.setImageUrl(entity.getImageUrl());
        model.setId(Long.valueOf(entity.getId()));
        return model;
    }
}