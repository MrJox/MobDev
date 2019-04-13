package com.company.Entities;

public class ProductEntity {
    private static int _id = 0;
    private int id;
    private String name;
    private int price;

    public ProductEntity(String name, int price) {
        this.id = _id++;
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
