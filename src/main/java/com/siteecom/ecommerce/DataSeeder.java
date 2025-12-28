package com.siteecom.ecommerce;

import com.siteecom.ecommerce.dataAccess.entity.CategoryEntity;
import com.siteecom.ecommerce.dataAccess.entity.ProductEntity;
import com.siteecom.ecommerce.dataAccess.repository.CategoryRepository;
import com.siteecom.ecommerce.dataAccess.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // Injection de dépendances via le constructeur
    public DataSeeder(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // On ne fait ça que si la base est vide pour éviter les doublons à chaque redémarrage
        if (categoryRepository.count() == 0) {
            System.out.println("--- Démarrage du Seeding (Insertion de données de test) ---");

            // 1. Création catégorie
            CategoryEntity catElec = new CategoryEntity("Électronique", "Electronics");
            catElec.setDescriptionFr("Tous vos gadgets préférés");
            catElec.setDescriptionEn("All your favorite gadgets");
            categoryRepository.save(catElec);

            // 2. Création produit
            ProductEntity laptop = new ProductEntity();
            laptop.setNameFr("PC Portable Gamer");
            laptop.setNameEn("Gaming Laptop");
            laptop.setSku("GAMER-PC-01");
            laptop.setPrice(new BigDecimal("1250.99"));
            laptop.setStockQuantity(10);
            laptop.setCategory(catElec); // Liaison Java

            productRepository.save(laptop);

            System.out.println("--- Données insérées : 1 Catégorie, 1 Produit ---");
        } else {
            System.out.println("--- Base de données déjà initialisée ---");
        }
    }
}