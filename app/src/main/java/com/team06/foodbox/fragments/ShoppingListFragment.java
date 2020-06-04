package com.team06.foodbox.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team06.foodbox.Adapter.ShoppingListAdapter;
import com.team06.foodbox.Model.ShoppingList;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;
import com.team06.foodbox.R;

import java.util.ArrayList;
import java.util.List;


public class ShoppingListFragment extends Fragment {


    RecyclerView recyclerView;
    public List<ShoppingList> shoppingListList=new ArrayList<>();
    ShoppingListAdapter shoppingListAdapter;
    FoodBoxDBHandler foodBoxDBHandler;

    public String ingredient;
    public String title;


    public ShoppingListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        prepareShopIngredientList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_shopping_list,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.shopping_recyclerview);

        foodBoxDBHandler=new FoodBoxDBHandler(getContext());

        shoppingListAdapter = new ShoppingListAdapter(getContext(),shoppingListList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpTodx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(shoppingListAdapter);


        return view;
    }



    private void prepareShopIngredientList() {

        shoppingListList.clear();
        List<ShoppingList> shoppingLists=foodBoxDBHandler.getAllShopping();
        if(shoppingLists!=null) {
            for (ShoppingList s : shoppingLists) {

                shoppingListList.add(s);
            }
        }

        shoppingListAdapter.notifyDataSetChanged();


    }


    private int dpTodx(int dp) {

        Resources resources = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }


    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spacing;
        private int spanCount;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {

            this.spacing = spacing;
            this.spanCount = spanCount;
            this.includeEdge = includeEdge;


        }


    }

}
