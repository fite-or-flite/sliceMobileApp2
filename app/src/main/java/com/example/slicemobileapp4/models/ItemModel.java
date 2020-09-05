package com.example.slicemobileapp4.models;

public class ItemModel {

    String name, description, smallPrice, mediumPrice, largePrice;

    public ItemModel() {
    }

    public ItemModel(String name, String description, String smallPrice, String mediumPrice, String largePrice) {
        this.name = name;
        this.description = description;
        this.smallPrice = smallPrice;
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallPrice() {
        return smallPrice;
    }

    public void setSmallPrice(String smallPrice) {
        this.smallPrice = smallPrice;
    }

    public String getMediumPrice() {
        return mediumPrice;
    }

    public void setMediumPrice(String mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    public String getLargePrice() {
        return largePrice;
    }

    public void setLargePrice(String largePrice) {
        this.largePrice = largePrice;
    }
}
