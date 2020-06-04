package com.team06.foodbox.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.team06.foodbox.CategoriesDetailsActivity;
import com.team06.foodbox.Model.CategoriesList;
import com.team06.foodbox.CategoriesActivity;
import com.team06.foodbox.R;

import java.util.List;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.MyViewHolder>{

    private CategoriesActivity mContext;
    private List<CategoriesList> categoriesListList;

    public CategoriesListAdapter(CategoriesActivity mContext, List<CategoriesList> categoriesListList) {
        this.mContext = mContext;
        this.categoriesListList = categoriesListList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView imgCategories;
        private CategoriesList categoriesList;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_categories_title);
            imgCategories = (ImageView) itemView.findViewById(R.id.img_categories_background);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), CategoriesDetailsActivity.class);
                    intent.putExtra("recipe_id",categoriesList.getRecipeId());
                    mContext.startActivity(intent);
                }
            });

        }

        public void bindData(CategoriesList categoriesList) {

            this.categoriesList = categoriesList;

            tvTitle.setText(categoriesList.getRecipeTitle());
            Glide.with(mContext).load(categoriesList.getRecipeImage()).apply(new RequestOptions().placeholder(R.drawable.curry)).into(imgCategories);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_list, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        CategoriesList categoriesList=categoriesListList.get(i);
        myViewHolder.bindData(categoriesList);
    }

    @Override
    public int getItemCount() {
        return categoriesListList.size();
    }


}
