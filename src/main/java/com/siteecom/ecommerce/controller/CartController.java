package com.siteecom.ecommerce.controller;

import com.siteecom.ecommerce.model.Cart;
import com.siteecom.ecommerce.model.Product;
import com.siteecom.ecommerce.service.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private Cart cart; // Spring injecte le panier DE LA SESSION en cours

    @Autowired
    private ProductServiceImpl productService;

    // 1. Afficher le panier
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", cart);
        return "cart"; // Renvoie vers cart.jsp
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Integer productId,
                            @RequestParam(value = "quantity", defaultValue = "1") int quantity,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes) { // <--- Ici

        Product product = productService.getProductById(productId);
        if (product != null) {
            cart.addItem(product, quantity);
            // Ajoute un message temporaire
            redirectAttributes.addFlashAttribute("successMessage", "Le produit " + product.getName() + " a été ajouté au panier !");
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
    
    // 3. Supprimer un item
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id) {
        cart.removeItem(id);
        return "redirect:/cart";
    }
}