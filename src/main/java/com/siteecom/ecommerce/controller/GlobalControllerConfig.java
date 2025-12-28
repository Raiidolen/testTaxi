package com.siteecom.ecommerce.controller;

import com.siteecom.ecommerce.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice // <--- Ceci s'applique à TOUS les contrôleurs
public class GlobalControllerConfig {

    @Autowired
    private Cart cart;

    @ModelAttribute("cart")
    public Cart populateCart() {
        return cart;
    }
}