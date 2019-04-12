package com.company;
import com.company.Handlers.GetCategoriesHandler;
import com.company.Entities.CategoryEntity;
import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class SimpleHttpServer {
    public static List<CategoryEntity> categories;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/getCategories", new GetCategoriesHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();

        setInitialCategories();
    }

    private static void setInitialCategories() {
        categories = new ArrayList<>();
        categories.add(new CategoryEntity("Comedy"));
        categories.add(new CategoryEntity("Horror"));
        categories.add(new CategoryEntity("Drama"));
        categories.add(new CategoryEntity("Detective"));
        categories.add(new CategoryEntity("Fantasy"));
        categories.add(new CategoryEntity("Fantastic"));
        categories.add(new CategoryEntity("Noir"));
        categories.add(new CategoryEntity("Historic"));
        categories.add(new CategoryEntity("Comics"));
        categories.add(new CategoryEntity("Thriller"));
        categories.add(new CategoryEntity("Adventures"));
    }
}
