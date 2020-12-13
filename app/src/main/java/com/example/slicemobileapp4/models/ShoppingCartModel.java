package com.example.slicemobileapp4.models;

public class ShoppingCartModel {
    String Name, Price, Instructions;
    String ToppingList, ToppingPrice;

    public ShoppingCartModel() {
    }

    public ShoppingCartModel(String name, String price, String instructions, String toppingList, String toppingPrice) {
        Name = name;
        Price = price;
        Instructions = instructions;
        ToppingList = toppingList;
        ToppingPrice = toppingPrice;
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

    public String getToppingList() {
        return ToppingList;
    }

    public void setToppingList(String toppingList) {
        ToppingList = toppingList;
    }

    public String getToppingPrice() {
        return ToppingPrice;
    }

    public void setToppingPrice(String toppingPrice) {
        ToppingPrice = toppingPrice;
    }
}
