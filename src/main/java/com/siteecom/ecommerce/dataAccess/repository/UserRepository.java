package com.siteecom.ecommerce.dataAccess.repository;

import com.siteecom.ecommerce.dataAccess.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Méthode magique : Spring génère le SQL basé sur "findByEmail"
    // On retourne un Optional car l'utilisateur peut ne pas exister
    Optional<User> findByEmail(String email);

    // Vérifie si un email existe déjà (utile pour l'inscription)
    boolean existsByEmail(String email);
}