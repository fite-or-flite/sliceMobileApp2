package com.example.slicemobileapp4.models;

public class ShoppingCartModel {
    String Name, Price;

    public ShoppingCartModel() {
    }

    public ShoppingCartModel(String name, String price) {
        Name = name;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
