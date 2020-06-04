package com.team06.foodbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.team06.foodbox.Adapter.CategoriesListAdapter;
import com.team06.foodbox.Model.CategoriesList;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;
import com.team06.foodbox.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<CategoriesList> recipeList=new ArrayList<>();
    private CategoriesListAdapter categoriesListAdapter;
    public int category_id;
    public FoodBoxDBHandler foodBoxDBHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodBoxDBHandler=new FoodBoxDBHandler(this);

        recyclerView = (RecyclerView) findViewById(R.id.categories_list_recyclerview);

        categoriesListAdapter = new CategoriesListAdapter(this,recipeList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoriesListAdapter);


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            category_id=bundle.getInt("category_id");
        }


        List<CategoriesList> recipeLists=foodBoxDBHandler.getAllRecipe(category_id);
        for(CategoriesList categories:recipeLists){

            recipeList.add(categories);

        }

        categoriesListAdapter.notifyDataSetChanged();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
