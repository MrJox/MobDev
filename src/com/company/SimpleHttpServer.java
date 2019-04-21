package com.company;
import com.company.Handlers.*;
import com.company.Entities.*;
import com.sun.net.httpserver.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;

public class SimpleHttpServer {
    public static List<CategoryEntity> categories;
    public static List<UserEntity> users;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/auth", new AuthHandler());
        server.createContext("/getCategories", new GetCategoriesHandler());
        server.createContext("/market/basket", new BasketHandler());
        server.createContext("/market/categories", new CategoriesHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        initUsers();
        initCategories();
    }

    private static void initUsers() {
        users = new ArrayList<>();
        users.add(new UserEntity(new CredentialsEntity("admin", "admin")));
    }

    private static void initCategories() {
        categories = new ArrayList<>();
        categories.add(new CategoryEntity("Accessories"));
        categories.add(new CategoryEntity("Smartphones"));
        categories.add(new CategoryEntity("Laptops"));
        categories.add(new CategoryEntity("Parts"));

        categories.get(0).addProduct(new ProductEntity("Sony Headsets", 2_000));
        categories.get(0).addProduct(new ProductEntity("Apple AirPods", 11_000));
        categories.get(0).addProduct(new ProductEntity("Apple AirPods 2", 18_000));
        categories.get(1).addProduct(new ProductEntity("iPhone 5", 12_000));
        categories.get(1).addProduct(new ProductEntity("iPhone 6", 16_000));
        categories.get(1).addProduct(new ProductEntity("iPhone 7", 25_000));
        categories.get(1).addProduct(new ProductEntity("iPhone 8", 35_000));
        categories.get(1).addProduct(new ProductEntity("iPhone SE", 20_000));
        categories.get(1).addProduct(new ProductEntity("iPhone X", 45_000));
        categories.get(1).addProduct(new ProductEntity("Samsung Galaxy S7", 19_990));
        categories.get(1).addProduct(new ProductEntity("Samsung Galaxy S8", 33_220));
        categories.get(1).addProduct(new ProductEntity("Samsung Galaxy S9", 41_525));
        categories.get(1).addProduct(new ProductEntity("Samsung Galaxy S10", 76_990));
        categories.get(2).addProduct(new ProductEntity("MacBook Air 2018", 104_990));
        categories.get(2).addProduct(new ProductEntity("MacBook Air 2019", 120_990));
        categories.get(2).addProduct(new ProductEntity("MacBook Pro 2018", 225_000));
        categories.get(2).addProduct(new ProductEntity("Lenovo G580", 20_690));
    }

    @Nullable
    public static BasketEntity findBasket(int basketId) {
        for (UserEntity user: users) {
            BasketEntity basket = user.getBasket();
            if (basket.getId() == basketId) {
                return basket;
            }
        }
        return null;
    }

    @Nullable
    public static ProductEntity findProduct(int categoryId, int productId) {
        for (CategoryEntity category: categories) {
            if (category.getId() == categoryId) {
                for (ProductEntity product : category.getProducts()) {
                    if (product.getId() == productId) {
                        return product;
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public static CategoryEntity findCategory(int categoryId) {
        for (CategoryEntity category: categories) {
            if (category.getId() == categoryId) {
                return category;
            }
        }
        return null;
    }
}
