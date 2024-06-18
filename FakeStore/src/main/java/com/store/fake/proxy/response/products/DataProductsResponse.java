package com.store.fake.proxy.response.products;


import lombok.Data;


@Data
public class DataProductsResponse {
    private int id;
    private String title;
    private double price;
    private String category;
    private String image;
    private RatinResonse rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RatinResonse getRating() {
        return rating;
    }

    public void setRating(RatinResonse rating) {
        this.rating = rating;
    }
}
