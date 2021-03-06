package com.company.Entities;
import java.util.ArrayList;
import java.util.List;

public class CategoryEntity {
    private static int _id = 0;
    private int id;
    private String name;
    private List<ProductEntity> products;

    public CategoryEntity(String name) {
        this.id = _id++;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(ProductEntity product) {
        this.products.add(product);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<ProductEntity> getProducts() {
        return this.products;
    }
}
