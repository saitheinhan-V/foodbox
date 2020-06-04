package com.team06.foodbox.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team06.foodbox.R;

import java.util.ArrayList;
import java.util.List;

import com.team06.foodbox.Adapter.HomeAdapter;
import com.team06.foodbox.Model.Home;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<Home> homeList = new ArrayList<>();

    private FoodBoxDBHandler foodBoxDBHandler;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_home);

        foodBoxDBHandler=new FoodBoxDBHandler(getContext());

        homeAdapter = new HomeAdapter(this, homeList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpTodx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeAdapter);

        prepareHomeList();

        return view;
    }

    private void prepareHomeList() {

        List<Home> homeLists = foodBoxDBHandler.getAllMethod();
        for (Home home : homeLists) {

            homeList.add(home);

        }
        homeAdapter.notifyDataSetChanged();
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





