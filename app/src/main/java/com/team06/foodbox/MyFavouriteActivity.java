package com.team06.foodbox;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.team06.foodbox.Model.Favourite;
import com.team06.foodbox.Adapter.FavouriteAdapter;
import com.team06.foodbox.R;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;

import java.util.ArrayList;
import java.util.List;

public class MyFavouriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private List<Favourite> favouriteList=new ArrayList<>();
    private FavouriteAdapter favouriteAdapter;

    private FoodBoxDBHandler foodBoxDBHandler;

    public String title;
    public String image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodBoxDBHandler=new FoodBoxDBHandler(this);

        recyclerView=(RecyclerView)findViewById(R.id.categories_recyclerview);
        favouriteAdapter = new FavouriteAdapter(this,favouriteList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(favouriteAdapter);


        prepareFavouriteList();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return true;
    }

    private void prepareFavouriteList() {

        List<Favourite> favouriteLists=foodBoxDBHandler.getAllFavourite();
        for(Favourite f:favouriteLists){

            favouriteList.add(f);
        }

        favouriteAdapter.notifyDataSetChanged();

    }

}
