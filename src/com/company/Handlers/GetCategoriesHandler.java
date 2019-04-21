package com.company.Handlers;
import com.company.SimpleHttpServer;
import com.company.Util.Methods;
import com.company.Util.StatusCodes;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.OutputStream;

public class GetCategoriesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        if (!httpExchange.getRequestMethod().equals(Methods.GET)) {
            headers.set("Content-Type", "text/html");
            httpExchange.sendResponseHeaders(StatusCodes.METHOD_NOT_ALLOWED, 0);
            return;
        }

        headers.set("Content-Type", "application/json");
        String json = new Gson().toJson(SimpleHttpServer.categories);
        httpExchange.sendResponseHeaders(StatusCodes.OK, json.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }
}
