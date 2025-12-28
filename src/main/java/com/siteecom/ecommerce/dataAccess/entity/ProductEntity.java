package com.siteecom.ecommerce.dataAccess.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String sku;

    @Column(name = "name_fr")
    private String nameFr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "description_fr")
    private String descriptionFr;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column
    private BigDecimal price; // BigDecimal pour l'argent (précision)

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private Boolean active = true;

    // Relation avec Category (Many Products -> One Category)
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    public ProductEntity() {}

    // Getters
    public Integer getId() { return id; }
    public String getNameFr() { return nameFr; }
    public String getNameEn() { return nameEn; }
    public String getDescriptionFr() { return descriptionFr; }
    public BigDecimal getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
    public String getSku() { return sku; }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public Boolean getActive() {
        return active;
    }

    public CategoryEntity getCategory() {
        return category;
    }


    // Setters... (Génère-les avec ton IDE)
    public void setNameFr(String nameFr) { this.nameFr = nameFr; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setDescriptionFr(String descriptionFr) { this.descriptionFr = descriptionFr; }

    public void setDescriptionEn(String descriptionEn) { this.descriptionEn = descriptionEn; }

    public void setNameEn(String nameEn) { this.nameEn = nameEn; }

    public void setSku(String sku) { this.sku = sku; }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}