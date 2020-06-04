package com.team06.foodbox.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.team06.foodbox.Model.CategoriesList;
import com.team06.foodbox.Model.Ingredient;
import com.team06.foodbox.SearchActivity;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;
import com.team06.foodbox.R;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    public AutoCompleteTextView tvSearch;
    public ImageButton imgBtnAdd;
    public Button btnSearch;
    public TextView tvIngredient;
    public ImageButton imgBtnClear;

    public StringBuilder builderTitle = new StringBuilder();
    public StringBuilder builderId = new StringBuilder();
    public StringBuilder builderImage = new StringBuilder();

    public final StringBuilder showBuilder = new StringBuilder();
    public final StringBuilder textBuilder = new StringBuilder();

    public int k = 0;

    public String name;
    public int recipeId;
    public String recipeTitle;
    public String recipeImage;

    public int id;
    public String recipeIngredient;
    public int count = 1;

    public ArrayList<String> myList = new ArrayList<>();
    public ArrayAdapter<String> arrayAdapter;

    public String[] ingredientArray;

    public FoodBoxDBHandler foodBoxDBHandler;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        foodBoxDBHandler = new FoodBoxDBHandler(getContext());

        tvSearch = view.findViewById(R.id.tv_search);
        imgBtnAdd = view.findViewById(R.id.img_button_add);
        btnSearch = view.findViewById(R.id.btn_search);
        tvIngredient = view.findViewById(R.id.tv_ingredients);
        imgBtnClear = view.findViewById(R.id.img_button_clear);


//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        SharedPreferences.Editor editor = preferences.edit();

        final StringBuilder builder = new StringBuilder();

        List<Ingredient> ingredientList = foodBoxDBHandler.getIngredient();
        for (Ingredient ingredient : ingredientList) {

            int id = ingredient.getId();
            name = ingredient.getName();

            Log.d("NAME", name);

            myList.add(name);
        }
        ingredientArray = myList.toArray(new String[myList.size()]);

        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, ingredientArray);
        tvSearch.setAdapter(arrayAdapter);


        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (String.valueOf(tvSearch.getText()).equals("")) {
                    Toast.makeText(getContext(), "ပါဝင်ပစ္စည်းများအရင်ရိုက်ပါ ", Toast.LENGTH_SHORT).show();
                } else {
                    if (count <= 8) {
                        showBuilder.append(tvSearch.getText() + "/");
                        textBuilder.append(  "* " + tvSearch.getText() + "\n");
                        tvIngredient.setText(textBuilder.toString());
                        count = count + 1;
                        tvSearch.setText("");
                    } else {
                        Toast.makeText(getContext(), " Can contain at most 8 ingredients!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (String.valueOf(tvIngredient.getText()).equals("")) {

                    Toast.makeText(getContext(), "ပါဝင်ပစ္စည်းများအရင်ရိုက်ပါ! ", Toast.LENGTH_SHORT).show();

                }
                else {

                    prepareSearch(showBuilder.toString());

                    if (builderId.toString().equals("")) {
                        Toast.makeText(getContext(), "ပြုလုပ်နည်းမရှိသေးပါ။", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(view.getContext(), SearchActivity.class);
                        intent.putExtra("search_id", builderId.toString());
                        intent.putExtra("search_title", builderTitle.toString());
                        intent.putExtra("search_image", builderImage.toString());
                        startActivity(intent);
                        Toast.makeText(getContext(), tvIngredient.getText() + " အတွက် ပြုလုပ်နည်းများရှိပါသည်။", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvIngredient.setText("");
                tvSearch.setText("");
                builderTitle.delete(0, builderTitle.length());
                builderImage.delete(0, builderImage.length());
                builderId.delete(0, builderId.length());
                showBuilder.delete(0, showBuilder.length());
                textBuilder.delete(0, textBuilder.length());
                count = 1;
            }
        });
        return view;

    }

    private void prepareSearch(String string) {

        String[] ingredientArr = string.split("/");

        List<CategoriesList> categoriesLists = foodBoxDBHandler.getRecipeIngredient();
        for (CategoriesList categoriesList : categoriesLists) {

            recipeId = categoriesList.getRecipeId();
            recipeTitle = categoriesList.getRecipeTitle();
            recipeImage = categoriesList.getRecipeImage();
            recipeIngredient = categoriesList.getRecipeIngredient();
            Log.d("RECIPE", recipeIngredient.toString());
            Log.d("IMAGE", recipeImage);


            String[] ingredient = recipeIngredient.split("/");

            int count = 1;
            //loop for ingredient typed by user
            outer:for (int m = 0; m < ingredientArr.length; m++) {

                //loop for ingredient from ingredient table
                inner:for (int n = 0; n < ingredient.length; n++) {

                    String splitIngredient = String.valueOf(ingredient[n]).split("-")[0];
                    Log.d("SPLIT", splitIngredient);

                    if (splitIngredient.equals(ingredientArr[m])) {

                        count=count+1;
                        break inner;
                    }

                }
            }
            if(count>1){
                builderId.append(String.valueOf(recipeId) + "/");

                builderTitle.append(recipeTitle + "/");

                builderImage.append(recipeImage + "/");
            }

        }


    }
}
