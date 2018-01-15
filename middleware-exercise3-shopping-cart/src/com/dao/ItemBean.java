package com.dao;

public class ItemBean {
    int id;
    String imageSource;
    float price;

    public ItemBean(int id, String imageSource, float price) {
        this.id = id;
        this.imageSource = imageSource;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getImageSource() {
        return imageSource;
    }

    public float getPrice() {
        return price;
    }
}
