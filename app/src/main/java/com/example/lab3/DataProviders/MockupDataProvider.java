package com.example.lab3.DataProviders;
import com.example.lab3.Interfaces.IDataProvider;
import com.example.lab3.Models.CategoryModel;
import java.util.ArrayList;

public class MockupDataProvider implements IDataProvider {
    private ArrayList<CategoryModel> categories;

    public MockupDataProvider() {
        categories = new ArrayList<>();
        categories.add(new CategoryModel("Mockup category"));
    }

    @Override
    public ArrayList<CategoryModel> fetchCategories() {
        return categories;
    }
}
