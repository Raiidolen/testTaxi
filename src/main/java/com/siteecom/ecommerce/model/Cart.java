package com.siteecom.ecommerce.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@SessionScope // <--- LA MAGIE EST ICI : Une instance par utilisateur connecté !
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    // Ajouter un produit
    public void addItem(Product product, int quantity) {
        // Vérifie si le produit est déjà dans le panier
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Si oui, on augmente la quantité
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // Sinon, on ajoute une nouvelle ligne
            items.add(new CartItem(product, quantity));
        }
    }

    // Supprimer un produit
    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    // Calculer le total du panier
    public Double getTotalAmount() {
        return items.stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }

    // Compter le nombre d'articles (pour le badge dans le menu)
    public int getItemCount() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public List<CartItem> getItems() { return items; }

    // Vider le panier (utile après la commande)
    public void clear() { items.clear(); }
}