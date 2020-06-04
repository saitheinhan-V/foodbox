package com.team06.foodbox.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.team06.foodbox.CategoriesDetailsActivity;
import com.team06.foodbox.Model.Search;
import com.team06.foodbox.R;
import com.team06.foodbox.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private Context mContext;
    private List<Search> searchList;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_list, viewGroup, false);

        return new SearchAdapter.MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView imgSearch;
        public Search search;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=(TextView)itemView.findViewById(R.id.tv_search_title);
            imgSearch=(ImageView)itemView.findViewById(R.id.img_search_background);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), CategoriesDetailsActivity.class);
                    intent.putExtra("recipe_id",Integer.parseInt(search.getSearchId()));
                    mContext.startActivity(intent);
                }
            });
        }

        public void bindData(Search search) {

            this.search=search;

            tvTitle.setText(search.getSearchTitle());
            Glide.with(mContext).load(search.getSearchImage()).apply(new RequestOptions().placeholder(R.drawable.curry)).into(imgSearch);
        }
    }
    public SearchAdapter(SearchActivity searchActivity, List<Search> searchList) {
        this.mContext=searchActivity;
        this.searchList=searchList;
    }

    public void updateData(ArrayList<Search> searchList){
        searchList.clear();
        searchList.addAll(searchList);
        notifyDataSetChanged();
    }
    public void addItem(int i,Search search){
        searchList.add(i,search);
        notifyItemInserted(i);
    }
    public void removeItem(int i){
        searchList.remove(i);
        notifyItemRemoved(i);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Search search=searchList.get(i);
        myViewHolder.bindData(search);
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

}
