package com.team06.foodbox.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.team06.foodbox.Model.ShoppingList;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;
import com.team06.foodbox.R;

import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder> {


    public Context mContext;
    public List<ShoppingList> shoppingListList;

    public ShoppingListAdapter(Context context, List<ShoppingList> shoppingListList) {
        this.mContext=context;
        this.shoppingListList=shoppingListList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ShoppingList shoppingList;

        private TextView tvShopTitle;
        private TextView tvShopIngredient;
        private ImageButton imgBtnClear;
        private FoodBoxDBHandler foodBoxDBHandler;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvShopTitle=itemView.findViewById(R.id.tv_shop_title);
            tvShopIngredient=itemView.findViewById(R.id.tv_shop_ingredient);
            imgBtnClear=itemView.findViewById(R.id.img_button_shop_clear);
            foodBoxDBHandler=new FoodBoxDBHandler(itemView.getContext());

            imgBtnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("SHOPPING",String.valueOf(shoppingList.getId()));
                    foodBoxDBHandler.deleteShopList(shoppingList.getTitle());

                    Toast.makeText(view.getContext(),shoppingList.getTitle()+" ကိုဖယ်ရှားပြီးပါပြီ",Toast.LENGTH_SHORT).show();

                    shoppingListList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),shoppingListList.size());
                }
            });
        }

        public void bindData(ShoppingList shoppingList) {

            this.shoppingList=shoppingList;
            tvShopTitle.setText(shoppingList.getTitle());
            tvShopIngredient.setText(shoppingList.getIngredient());
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.shopping_list_cardview, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final ShoppingList shoppingList = shoppingListList.get(i);
        myViewHolder.bindData(shoppingList);

    }

    @Override
    public int getItemCount() {
        return shoppingListList.size();
    }

}
