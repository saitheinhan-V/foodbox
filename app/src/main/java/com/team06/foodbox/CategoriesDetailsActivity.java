package com.team06.foodbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.team06.foodbox.Model.CategoriesList;
import com.team06.foodbox.Model.Favourite;
import com.team06.foodbox.Model.ShoppingList;
import com.team06.foodbox.R;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;
import com.team06.foodbox.fragments.ShoppingListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CategoriesDetailsActivity extends AppCompatActivity {

    private Button btnDirection,btnShoppingList;
    private ImageView imgRecipe;
    private TextView tvRecipeTitle,tvAllIngredient;
    private ImageView imgFavourite;

    public int recipe_id;
    public boolean isChecked=true;
    public boolean flag=false;

    public FoodBoxDBHandler foodBoxDBHandler;

    private String direction;
    private String ingredient;
    private String title;
    private String image;
    private String[] ingredientArray={};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodBoxDBHandler=new FoodBoxDBHandler(this);

        imgFavourite=findViewById(R.id.img_favourite);
        imgRecipe=findViewById(R.id.img_categories);
        tvRecipeTitle=findViewById(R.id.tv_categories_title);
        tvAllIngredient=findViewById(R.id.tv_all_ingredient);

        btnShoppingList=(Button)findViewById(R.id.btn_shopping_list);
        btnDirection=(Button)findViewById(R.id.btn_directions);


        final Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            recipe_id=bundle.getInt("recipe_id");
        }

        List<CategoriesList> recipeLists=foodBoxDBHandler.getAllRecipeDetails(recipe_id);
        for(CategoriesList categories:recipeLists){

            title=categories.getRecipeTitle();
            image=categories.getRecipeImage();
            direction=categories.getRecipeDirection();
            ingredient=categories.getRecipeIngredient();
            ingredientArray=ingredient.split("/");

            StringBuilder builder=new StringBuilder();
            for(int i=0;i<ingredientArray.length;i++){
                builder.append("("+(i+1)+") "+ingredientArray[i]+"\n");
            }

            Glide.with(getApplicationContext()).load(categories.getRecipeImage()).apply(new RequestOptions().placeholder(R.drawable.curry)).into(imgRecipe);
            tvRecipeTitle.setText(title);
            tvAllIngredient.setText(builder.toString());
        }


            btnShoppingList.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View view) {


                    final String[] splitIngredient=new String[ingredientArray.length];
                    final boolean[] checked=new boolean[ingredientArray.length];
                    for(int k=0;k<ingredientArray.length;k++){

                        splitIngredient[k]=String.valueOf(ingredientArray[k]).split("-")[0];
                        checked[k]=false;
                    }

                    final StringBuilder stringBuilder=new StringBuilder();
                    final List<String> arrayList= Arrays.asList(splitIngredient);
                    final List<String> showList=new ArrayList<>();
                    AlertDialog.Builder builder=new AlertDialog.Builder(CategoriesDetailsActivity.this);

                    builder.setTitle("ပါဝင်စ္စည်းများကို ရွေးပါ");
                    builder.setMultiChoiceItems(splitIngredient,checked, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                            if(b){
                                String checkString=arrayList.get(i);
                               // stringBuilder.append(checkString+"/");
                                showList.add(checkString);

                                checked[i]=true;
                            }else{
                                showList.remove(i);
                                checked[i]=false;
                            }


                        }
                    });
                    builder.setCancelable(false);
                    builder.setPositiveButton("ထည့်မယ်", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            if(showList.size()!=0) {
                                insertShopIngredient(title, showList.toString());
                                Toast.makeText(getApplicationContext(),"ဈေးဝယ်စာရင်းသို့ထည့်ပြီး",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(),"ပါဝင်ပစ္စည်းအရင်ရွေးချယ်ပါ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("မထည့်ပါ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog=builder.create();
                    dialog.getWindow().getDecorView().setBackgroundColor(Color.parseColor("#c0d6e4"));
                    dialog.show();


                    }

            });


        //check if the recipe is whether in favorite table or not
        StringBuilder builder=new StringBuilder();
        List<Favourite> favouriteLists=foodBoxDBHandler.getAllFavourite();
        for(Favourite f:favouriteLists){

            builder.append(f.getRecipeId()+"/");

        }
        String[] favouriteArr=builder.toString().split("/");
       check: for(int k=0;k<favouriteArr.length;k++){

            if(favouriteArr[k].contentEquals(String.valueOf(recipe_id))){
                imgFavourite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                flag=true;
                break check;
            }
        }


        imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isChecked && !flag) {

                    imgFavourite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);

                    Snackbar.make(view, title+ " ကိုမိမိအကြိုက်သို့ထည့်ပြီး",Snackbar.LENGTH_SHORT)
                            .setAction("ကြည့်မယ်", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(getApplicationContext(),MyFavouriteActivity.class);
                                    startActivity(intent);
                                }
                            }).show();

                    isChecked=false;
                    flag=true;

                    Favourite favourite=new Favourite(recipe_id,title,image);
                    foodBoxDBHandler.addFavourite(favourite);

                }else {
                    imgFavourite.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);

                    Snackbar.make(view, title+" ကိုမိမိအကြိုက်မှ ဖယ်ရှားပြီး",Snackbar.LENGTH_SHORT).setAction("Action",null).show();
                    isChecked=true;
                    flag=false;

                    foodBoxDBHandler.deleteFavourite(recipe_id);
                }
            }

        });


        //show direction for each recipe
        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoriesDetailsActivity.this,CategoriesDirectionActivity.class);
                intent.putExtra("direction",direction);
                startActivity(intent);
            }
        });


    }


    private void insertShopIngredient(String shop_title,String shop_ingredient) {


            String[] ingredientArr=shop_ingredient.split(",");
            StringBuilder stringBuilder=new StringBuilder();
            for(int k=0;k<ingredientArr.length;k++){

                String string=String.valueOf(ingredientArr[k]).split("-")[0];

                if(k==ingredientArray.length){
                    String last=string.split("]")[0];
                    stringBuilder.append(last+"\n");
                }
                else{
                       // String first=string.split("[||]")[1];
                }
                stringBuilder.append("("+(k+1)+") "+string+"\n");
            }
            ShoppingList shoppingList=new ShoppingList(shop_title,stringBuilder.toString());
            foodBoxDBHandler.addShopping(shoppingList);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return true;
    }



}
