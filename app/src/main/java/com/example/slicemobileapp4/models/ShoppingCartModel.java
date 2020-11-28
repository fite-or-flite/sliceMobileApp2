package com.example.slicemobileapp4.models;

public class ShoppingCartModel {
    String Name, Price, Instructions;

    public ShoppingCartModel() {
    }

    public ShoppingCartModel(String name, String price, String instructions) {
        Name = name;
        Price = price;
        Instructions = instructions;
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

    public  String getInstructions() { return Instructions; }

    public void setInstructions(String instructions) { Instructions = instructions; }
}
