package com.example.lab3.DataProviders;
import com.example.lab3.API.*;
import com.example.lab3.Models.*;
import java.util.HashMap;
import java.util.Map;

public class RestDataProvider implements IDataProvider {
    public RestDataProvider() { }

    @Override
    public ResponseModel authorize(CredentialsModel credentials) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("login", credentials.getLogin());
            body.put("pswd", credentials.getPswd());
            return new RequestManager(Constants.AUTHORIZE, Methods.POST, body).execute().get();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseModel signup(CredentialsModel credentials) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("login", credentials.getLogin());
            body.put("pswd", credentials.getPswd());
            return new RequestManager(Constants.SIGNUP, Methods.POST, body).execute().get();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
