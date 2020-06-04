package com.team06.foodbox.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.team06.foodbox.Model.Categories;
import com.team06.foodbox.CategoriesActivity;
import com.team06.foodbox.R;
import com.team06.foodbox.fragments.CategoriesFragment;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private CategoriesFragment mContext;
    private List<Categories> categoriesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public ImageView imgCategories;
        public Categories categories;

        public MyViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tv_categories_title);
            imgCategories = (ImageView) view.findViewById(R.id.img_categories);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), CategoriesActivity.class);
                    intent.putExtra("category_id",categories.getCategoriesId());
                    mContext.startActivity(intent);
                }
            });

        }

        public void bindData(Categories categories) {

            this.categories = categories;

            tvTitle.setText(categories.getCategoriesTitle());
            Glide.with(mContext).load(categories.getCategoriesImage()).apply(new RequestOptions().placeholder(R.drawable.curry)).into(imgCategories);

        }


    }

    public CategoriesAdapter(CategoriesFragment mContext, List<Categories> categoriesList) {
        this.mContext = mContext;
        this.categoriesList = categoriesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Categories categories = categoriesList.get(position);
        holder.bindData(categories);

        holder.itemView.setClickable(true);

    }


    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

}
