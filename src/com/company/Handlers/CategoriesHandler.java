package com.company.Handlers;
import com.company.Entities.*;
import com.company.SimpleHttpServer;
import com.company.Util.*;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");
        int statusCode;
        String response;
        Map<String, Object> data = new HashMap<>();

        if (!httpExchange.getRequestMethod().equals(Methods.GET)) {
            statusCode = StatusCodes.METHOD_NOT_ALLOWED;
        } else {
            String path = httpExchange.getRequestURI().getPath();
            String[] nodesArray = path.split("/");
            List<String> nodes = Arrays.asList(nodesArray).subList(1, nodesArray.length);

            switch (nodes.size()) {
                case 2: {
                    data.put("categories", SimpleHttpServer.categories);
                    statusCode = StatusCodes.OK;
                    break;
                }
                case 4: {
                    String resource = nodes.get(3);
                    if (resource.equals("goods")) {
                        int categoryId = Integer.parseInt(nodes.get(2));
                        List<ProductEntity> goods = SimpleHttpServer.findCategory(categoryId).getProducts();
                        data.put("goods", goods);
                        statusCode = StatusCodes.OK;
                    } else {
                        statusCode = StatusCodes.NOT_FOUND;
                    }
                    break;
                }
                case 5: {
                    String resource = nodes.get(3);
                    if (resource.equals("goods")) {
                        int categoryId = Integer.parseInt(nodes.get(2));
                        int productId = Integer.parseInt(nodes.get(4));
                        ProductEntity product = SimpleHttpServer.findProduct(categoryId, productId);
                        if (product != null) {
                            data.put("product", product);
                            statusCode = StatusCodes.OK;
                        } else {
                            statusCode = StatusCodes.NOT_FOUND;
                        }
                    } else {
                        statusCode = StatusCodes.NOT_FOUND;
                    }
                    break;
                }
                default:
                    statusCode = StatusCodes.NOT_FOUND;
            }
        }

        httpExchange.sendResponseHeaders(statusCode, 0);
        response = new Gson().toJson(new ResponseEntity(statusCode, data));
        try (final OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
