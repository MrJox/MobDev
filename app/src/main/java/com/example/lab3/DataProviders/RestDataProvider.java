package com.example.lab3.DataProviders;
import com.example.lab3.API.*;
import com.example.lab3.Interfaces.IDataProvider;
import com.example.lab3.Models.CategoryModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RestDataProvider implements IDataProvider {
    ArrayList<CategoryModel> categories;

    public RestDataProvider() {
        categories = new ArrayList<>();
    }

    @Override
    public ArrayList<CategoryModel> fetchCategories() throws IOException {
        try {
            String json = new HttpUrlConnectionReader(Constants.GET_CATEGORIES, "GET").execute().get();
            Type type = new TypeToken<ArrayList<CategoryModel>>() {}.getType();
            categories = new Gson().fromJson(json, type);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categories;
    }
}
