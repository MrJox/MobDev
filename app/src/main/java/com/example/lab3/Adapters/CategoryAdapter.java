package com.example.lab3.Adapters;
import android.content.Context;
import android.view.*;
import android.widget.*;
import com.example.lab3.Models.CategoryModel;
import com.example.lab3.R;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CategoryModel> {
    private int layout;
    private List<CategoryModel> categories;

    public CategoryAdapter(Context context, int layout, List<CategoryModel> categories) {
        super(context, layout, categories);
        this.layout = layout;
        this.categories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(layout, parent, false);
        TextView tvCategoryName = view.findViewById(R.id.categoryName);
        CategoryModel categoryModel = categories.get(position);

        if (categoryModel != null) {
            tvCategoryName.setText(categoryModel.getName());
        }

        return view;
    }
}
