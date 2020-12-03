package com.example.slicemobileapp4.models;

public class ToppingsModel {
    String Name;
    Long Cost;

    public ToppingsModel() {
    }

    public ToppingsModel(String name, Long cost) {
        this.Name = name;
        this.Cost = cost;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getCost() {
        return Cost;
    }

    public void setCost(Long cost) {
        Cost = cost;
    }
}
