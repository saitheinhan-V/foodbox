package com.team06.foodbox.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.team06.foodbox.Model.Favourite;
import com.team06.foodbox.R;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {

    private Context mContext;
    private List<Favourite> favouriteList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView favImage;
        public TextView tvDelete;
        public Favourite favourite;
        public FoodBoxDBHandler foodBoxDBHandler;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.tv_favourite_title);
            favImage=(ImageView)itemView.findViewById(R.id.img_favourite_background);
            tvDelete=(TextView) itemView.findViewById(R.id.tv_delete);
            foodBoxDBHandler=new FoodBoxDBHandler(mContext);

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(mContext,R.style.DialogStyle);
                    builder.setMessage("ဖျက်မှာသေချာပြီလား!");
                    builder.setPositiveButton("ဖျက်မယ်", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        foodBoxDBHandler.deleteFavourite(favourite.getRecipeId());
                        favouriteList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(),favouriteList.size());

                        }
                    });

                    builder.setNegativeButton("မဖျက်ပါ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext(), CategoriesDetailsActivity.class);
                    intent.putExtra("recipe_id",favourite.getRecipeId());
                    mContext.startActivity(intent);
                }
            });

        }

        public void bindData(Favourite favourite) {

            this.favourite=favourite;

            title.setText(favourite.getTitle());
            Glide.with(mContext).load(favourite.getFavImage()).apply(new RequestOptions().placeholder(R.drawable.curry)).into(favImage);

        }
    }

    @NonNull
    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favourite_list, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Favourite favourite=favouriteList.get(i);
        myViewHolder.bindData(favourite);

    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }


    public FavouriteAdapter(Context mContext, List<Favourite> favouriteList) {
        this.mContext = mContext;
        this.favouriteList = favouriteList;
    }

}
