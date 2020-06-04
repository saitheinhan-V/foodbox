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
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.team06.foodbox.Adapter.CategoriesAdapter;
import com.team06.foodbox.Model.Categories;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;
import com.team06.foodbox.R;

import java.util.ArrayList;
import java.util.List;


public class CategoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoriesAdapter adapter;
    private List<Categories> categoriesList = new ArrayList<>();
    private ViewFlipper viewFlipper;

    private FoodBoxDBHandler foodBoxDBHandler;

    int[] slide = new int[]{R.drawable.slide1, R.drawable.slide2, R.drawable.slide4, R.drawable.slide3};


    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        foodBoxDBHandler = new FoodBoxDBHandler(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.categories_recyclerview);

        adapter = new CategoriesAdapter(this, categoriesList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpTodx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        viewFlipper = view.findViewById(R.id.view_flipper);

        for (int image : slide) {
            FlipImage(image);
        }

        prepareCategories();

        return view;

    }

    public void FlipImage(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }

    private void prepareCategories() {

        List<Categories> categoryList = foodBoxDBHandler.getAllCategories();
        for (Categories categories : categoryList) {

            categoriesList.add(categories);

        }
        adapter.notifyDataSetChanged();

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



