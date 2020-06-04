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
import com.team06.foodbox.MethodActivity;
import com.team06.foodbox.Model.Home;
import com.team06.foodbox.R;

import java.util.List;

import com.team06.foodbox.fragments.HomeFragment;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private HomeFragment mContext;
    private List<Home> homeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public ImageView imgHome;
        public Home home;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=(TextView)itemView.findViewById(R.id.tv_home_title);
            imgHome=(ImageView)itemView.findViewById(R.id.img_home_list);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(v.getContext(),MethodActivity.class);
                   intent.putExtra("method_description",home.getDescription());
                   intent.putExtra("method_title",home.getTitle());
                   intent.putExtra("method_image",home.getImage());
                   mContext.startActivity(intent);
               }
           });

        }

        public void bindData(Home _home) {

            this.home=_home;

            tvTitle.setText(home.getTitle());
            Glide.with(mContext).load(home.getImage()).apply(new RequestOptions().placeholder(R.drawable.curry)).into(imgHome);
        }
    }

    public HomeAdapter(HomeFragment mContext, List<Home> homeList) {
        this.mContext = mContext;
        this.homeList = homeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_list, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Home home=homeList.get(i);
        myViewHolder.bindData(home);

    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }


}
