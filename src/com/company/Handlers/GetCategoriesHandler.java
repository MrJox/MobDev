package com.company.Handlers;
import com.company.SimpleHttpServer;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.OutputStream;

public class GetCategoriesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        if (!httpExchange.getRequestMethod().equals("GET")) {
            headers.set("Content-Type", "text/html");
            httpExchange.sendResponseHeaders(404, 0);
            return;
        }

        headers.set("Content-Type", "application/json");
        String json = new Gson().toJson(SimpleHttpServer.categories);
        httpExchange.sendResponseHeaders(200, json.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }
}
