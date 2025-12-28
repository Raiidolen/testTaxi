package com.siteecom.ecommerce.model;

public class Product {
    private Long id; // Utile pour plus tard
    private String name;
    private Double price;
    private String description;
    private String imageUrl; // Nom du fichier image (ex: renard.jpg)

    public Product() {}

    public Product(Long id, String name, Double price, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public Long getId() { return id; }

    //setter


    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}