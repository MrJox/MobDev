package com.company.Handlers;
import com.company.Entities.CredentialsEntity;
import com.company.Entities.ResponseEntity;
import com.company.Entities.UserEntity;
import com.company.SimpleHttpServer;
import com.company.Util.*;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AuthHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");
        int statusCode;
        String response;

        if (!httpExchange.getRequestMethod().equals(Methods.POST)) {
            statusCode = StatusCodes.METHOD_NOT_ALLOWED;
        } else {
            String strategy = httpExchange.getRequestURI().getPath().substring("/auth/".length());
            switch (strategy) {
                case "login": {
                    InputStream is = httpExchange.getRequestBody();
                    CredentialsEntity credentials = new Gson().fromJson(BodyReader.readBody(is), CredentialsEntity.class);
                    boolean isValid = validate(credentials);
                    statusCode = isValid ? StatusCodes.OK : StatusCodes.BAD_CREDENTIALS;
                    break;
                }
                case "registration": {
                    InputStream is = httpExchange.getRequestBody();
                    CredentialsEntity credentials = new Gson().fromJson(BodyReader.readBody(is), CredentialsEntity.class);
                    boolean didSignup = signup(credentials);
                    statusCode = didSignup ? StatusCodes.OK : StatusCodes.CONFLICT;
                    break;
                }
                default: statusCode = StatusCodes.NOT_FOUND;
            }
        }

        httpExchange.sendResponseHeaders(statusCode, 0);
        response = new Gson().toJson(new ResponseEntity(statusCode, null));
        try (final OutputStream os = httpExchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    private boolean validate(CredentialsEntity credentials) {
        if (credentials.getLogin() == null || credentials.getPswd() == null) {
            return false;
        }
        
        for (UserEntity existingUser: SimpleHttpServer.users) {
            if (existingUser.getCredentials().equals(credentials))
                return existingUser.authorize();
        }
        return false;
    }

    private boolean signup(CredentialsEntity credentials) {
        if (credentials.getLogin() == null || credentials.getPswd() == null) {
            return false;
        }

        for (UserEntity existingUser: SimpleHttpServer.users) {
            if (existingUser.getCredentials().equals(credentials)) {
                return false;
            }
        }
        SimpleHttpServer.users.add(new UserEntity(credentials));
        return true;
    }
}
