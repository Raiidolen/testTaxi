package com.siteecom.ecommerce.controller;

import com.siteecom.ecommerce.model.Product;
import com.siteecom.ecommerce.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(value = "search", required = false) String search,
                       @RequestParam(value = "categoryId", required = false) Integer categoryId,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "6") int size) { // 6 produits par page par défaut

        // 1. Charger les produits filtrés et paginés
        Page<Product> productPage = productService.searchProducts(categoryId, search, page, size);

        // 2. Charger la liste des catégories pour le filtre
        model.addAttribute("categories", productService.getAllCategories());

        // 3. Envoyer tout ça à la vue
        model.addAttribute("productPage", productPage);
        model.addAttribute("products", productPage.getContent()); // Pour garder la compatibilité avec ta boucle c:forEach existante

        // On renvoie aussi les filtres actuels pour garder le champ rempli dans la barre de recherche
        model.addAttribute("currentSearch", search);
        model.addAttribute("currentCategoryId", categoryId);

        return "home";
    }
}