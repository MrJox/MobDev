package com.example.lab3.Interfaces;
import com.example.lab3.Models.CategoryModel;

import java.io.IOException;
import java.util.ArrayList;

public interface IDataProvider {
    ArrayList<CategoryModel> fetchCategories() throws IOException;
}
