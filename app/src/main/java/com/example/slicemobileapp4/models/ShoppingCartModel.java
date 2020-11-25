package com.example.slicemobileapp4.models;

public class ShoppingCartModel {
    String Name, Price, Description;

    public ShoppingCartModel() {
    }

    public ShoppingCartModel(String name, String price, String description) {
        Name = name;
        Price = price;
        Description = description;
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

    public  String getDescription() { return Description; }

    public void setDescription(String description) { Description = description; }
}
