package com.company.Handlers;
import com.company.Entities.*;
import com.company.SimpleHttpServer;
import com.company.Util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.*;
import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class BasketHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");
        int statusCode;
        String response;
        Map<String, Object> data = new HashMap<>();

        String strategy = httpExchange.getRequestURI().getPath().substring("/market/basket/".length());
        switch (strategy) {
            case "add":
            case "remove": {
                if (!httpExchange.getRequestMethod().equals(Methods.POST)) {
                    statusCode = StatusCodes.METHOD_NOT_ALLOWED;
                    break;
                }

                Type type = new TypeToken<Map<String, String>>(){}.getType();
                String json = BodyReader.readBody(httpExchange.getRequestBody());
                Map<String, String> params = new Gson().fromJson(json, type);

                int basketId = Integer.parseInt(params.get("basketId"));
                int productId = Integer.parseInt(params.get("productId"));
                int categoryId = Integer.parseInt(params.get("categoryId"));
                BasketEntity basket = SimpleHttpServer.findBasket(basketId);
                ProductEntity product = SimpleHttpServer.findProduct(categoryId, productId);

                if (basket != null && product != null) {
                    statusCode = StatusCodes.OK;
                    if (strategy.equals("add")) {
                        basket.add(product);
                    } else {
                        basket.remove(product);
                    }
                } else {
                    statusCode = StatusCodes.BAD_REQUEST;
                }
                break;
            }
            case "checkout": {
                if (!httpExchange.getRequestMethod().equals(Methods.GET)) {
                    statusCode = StatusCodes.METHOD_NOT_ALLOWED;
                    break;
                }

                String query = httpExchange.getRequestURI().getQuery();
                Map<String, String> params = QueryReader.getParams(query);

                int basketId = Integer.parseInt(params.get("basketId"));
                BasketEntity basket = SimpleHttpServer.findBasket(basketId);

                if (basket != null) {
                    statusCode = StatusCodes.OK;
                    int totalPrice = basket.checkout();
                    data.put("totalPrice", Integer.toString(totalPrice));
                } else {
                    statusCode = StatusCodes.BAD_REQUEST;
                }
                break;
            }
            default: statusCode = StatusCodes.NOT_FOUND;
        }

        httpExchange.sendResponseHeaders(statusCode, 0);
        response = new Gson().toJson(new ResponseEntity(statusCode, data));
        try (final OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
