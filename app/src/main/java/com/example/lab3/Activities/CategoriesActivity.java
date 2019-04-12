package com.example.lab3.Activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lab3.Adapters.CategoryAdapter;
import com.example.lab3.DataProviders.*;
import com.example.lab3.Interfaces.IDataProvider;
import com.example.lab3.Models.CategoryModel;
import com.example.lab3.R;

import java.io.IOException;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    private ListView lvCategoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        lvCategoriesList = findViewById(R.id.categoriesList);

        IDataProvider dataProvider = new RestDataProvider();
        try {
            setCategoryModelList(dataProvider.fetchCategories());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCategoryModelList(List<CategoryModel> categoryModelList) {
        CategoryAdapter adapter = new CategoryAdapter(
                this, R.layout.category, categoryModelList);

        lvCategoriesList.setAdapter(adapter);
    }
}
