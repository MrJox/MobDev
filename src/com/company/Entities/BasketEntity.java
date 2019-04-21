package com.company.Entities;
import java.util.ArrayList;
import java.util.List;

public class BasketEntity {
    private static int _id = 0;
    private int id;
    private List<ProductEntity> products;

    public BasketEntity() {
        this.id = _id++;
        this.products = new ArrayList<>();
    }

    public void add(ProductEntity product) {
        this.products.add(product);
    }

    public void remove(ProductEntity product) {
        this.products.remove(product);
    }

    public void remove(int id) {
        for (ProductEntity product: this.products) {
            if (product.getId() == id)
                this.products.remove(product);
        }
    }

    public int checkout() {
        int totalPrice = 0;
        for (ProductEntity product: this.products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public int getId() {
        return this.id;
    }
}
