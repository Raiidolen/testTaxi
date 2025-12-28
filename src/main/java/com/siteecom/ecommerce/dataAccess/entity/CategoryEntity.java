package com.siteecom.ecommerce.dataAccess.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_fr", nullable = false)
    private String nameFr;

    @Column(name = "name_en", nullable = false)
    private String nameEn;

    @Column(name = "description_fr", columnDefinition = "TEXT")
    private String descriptionFr;

    @Column(name = "description_en", columnDefinition = "TEXT")
    private String descriptionEn;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductEntity> productEntities = new ArrayList<>();

    // --- Constructeurs ---
    public CategoryEntity() {}

    public CategoryEntity(String nameFr, String nameEn) {
        this.nameFr = nameFr;
        this.nameEn = nameEn;
    }

    // --- Méthodes Métier (Helper) ---
    public String getName(String locale) {
        if ("en".equals(locale)) return nameEn;
        return nameFr;
    }

    public String getDescription(String locale) {
        if ("en".equals(locale)) return descriptionEn;
        return descriptionFr;
    }

    // --- Getters et Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNameFr() { return nameFr; }
    public void setNameFr(String nameFr) { this.nameFr = nameFr; }

    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public String getDescriptionFr() { return descriptionFr; }
    public void setDescriptionFr(String descriptionFr) { this.descriptionFr = descriptionFr; }

    public String getDescriptionEn() { return descriptionEn; }
    public void setDescriptionEn(String descriptionEn) { this.descriptionEn = descriptionEn; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<ProductEntity> getProducts() { return productEntities; }
    public void setProducts(List<ProductEntity> productEntities) { this.productEntities = productEntities; }

    // --- toString ---
    @Override
    public String toString() {
        // ATTENTION : Ne jamais afficher la liste 'products' ici (boucle infinie)
        return "Category{id=" + id + ", nameFr='" + nameFr + "'}";
    }
}