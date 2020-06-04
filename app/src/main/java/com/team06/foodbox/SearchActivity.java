package com.team06.foodbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.team06.foodbox.Adapter.SearchAdapter;
import com.team06.foodbox.Model.Search;
import com.team06.foodbox.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ImageView imgSearch;
    TextView tvSearch;
    List<Search> searchList=new ArrayList<>();
    SearchAdapter searchAdapter;

    String[] searchArrId={};
    String[] searchArrTitle={};
    String[] searchArrImage={};
    String search_id;
    String search_title;
    String search_image;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.search_list_recyclerview);
        tvSearch=findViewById(R.id.tv_search_title);
        imgSearch=findViewById(R.id.img_search_background);

        searchAdapter = new SearchAdapter(this,searchList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(searchAdapter);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            search_id=bundle.getString("search_id");
            search_title=bundle.getString("search_title");
            search_image=bundle.getString("search_image");
        }

        if(search_id!=null) {
            prepareSearchList(search_id, search_title, search_image);
        }

    }

    private void clearData() {
        searchList.clear();
        searchAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(searchAdapter);
    }


    private void prepareSearchList(String search_id,String search_title,String search_image) {

        clearData();
        searchArrId=search_id.split("/");
        Log.d("ALL",searchArrId.toString());
        searchArrTitle=search_title.split("/");
        searchArrImage=search_image.split("/");

        for(int i=0;i<searchArrId.length;i++){

            Search search=new Search(searchArrId[i],searchArrTitle[i],searchArrImage[i]);
            searchList.add(search);

        }
        searchAdapter.notifyDataSetChanged();
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
