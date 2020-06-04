package com.team06.foodbox;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.team06.foodbox.Model.Categories;
import com.team06.foodbox.Model.CategoriesList;
import com.team06.foodbox.Model.Home;
import com.team06.foodbox.Model.Ingredient;
import com.team06.foodbox.R;
import com.team06.foodbox.dbHandler.FoodBoxDBHandler;
import com.team06.foodbox.fragments.CategoriesFragment;
import com.team06.foodbox.fragments.HomeFragment;
import com.team06.foodbox.fragments.SearchFragment;
import com.team06.foodbox.fragments.ShoppingListFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public FoodBoxDBHandler foodBoxDBHandler;

    private int[] tabIcons = {
            R.drawable.ic_home_24dp,
            R.drawable.ic_format_list_24dp,
            R.drawable.ic_search_24dp,
            R.drawable.ic_add_shopping_cart_24dp

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodBoxDBHandler = new FoodBoxDBHandler(this);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        insertCategory();
        insertRecipe();
        insertIngredient();
        insertMethod();

    }

    private void insertMethod() {

        try {
            JSONArray arrJSON = new JSONArray (loadJSONMethodFromAsset());
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                Home home=new Home(
                        objJSON.getInt("id"),
                        objJSON.getString("title"),
                        objJSON.getString("description"),
                        objJSON.getString("image"));

                foodBoxDBHandler.addMethod(home);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONMethodFromAsset() {

        StringBuilder stringBuilder=new StringBuilder();
        try{
            InputStream is=getAssets().open("method.json");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

            Log.d(" RECEIVE ", stringBuilder.toString());

            return stringBuilder.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void insertIngredient() {

        try {
            JSONArray arrJSON = new JSONArray (loadJSONIngredientFromAsset());
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                Ingredient ingredient=new Ingredient(objJSON.getInt("ingredient_id"),
                        objJSON.getString("ingredient_name"));
                foodBoxDBHandler.addIngredient(ingredient);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONIngredientFromAsset() {
        StringBuilder stringBuilder=new StringBuilder();
        try{
            InputStream is=getAssets().open("ingredient.json");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

            Log.d(" RECEIVE ", stringBuilder.toString());

            return stringBuilder.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void insertRecipe() {
        try {
            JSONArray arrJSON = new JSONArray (loadJSONRecipeFromAsset());
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                CategoriesList categoriesList=new CategoriesList(
                        objJSON.getInt("recipe_id"),
                        objJSON.getInt("categories_id"),
                        objJSON.getString("recipe_title"),objJSON.getString("recipe_image"),objJSON.getString("recipe_ingredient"),objJSON.getString("recipe_direction"));
                foodBoxDBHandler.addRecipe(categoriesList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONRecipeFromAsset() {

        StringBuilder stringBuilder=new StringBuilder();
        try{
            InputStream is=getAssets().open("recipe.json");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

            Log.d("GET ", stringBuilder.toString());

            return stringBuilder.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void insertCategory() {
        try {
            JSONArray arrJSON = new JSONArray (loadJSONFromAsset());
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject objJSON = arrJSON.getJSONObject(i);

                Categories categories = new Categories(objJSON.getInt("category_id"),
                        objJSON.getString("category_title"),
                        objJSON.getString("category_image")
                );
                //categoriesList.add(categories);
                foodBoxDBHandler.addCategory(categories);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset() {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is=getAssets().open("category.json") ;
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

            Log.d("RESPONSE ", stringBuilder.toString());

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.action_favourite) {

            Intent intent = new Intent(MainActivity.this, MyFavouriteActivity.class);
            startActivity(intent);

        }else if(id==R.id.action_about){

            Intent intent=new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);

        }else if(id==R.id.action_exit){

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this,R.style.DialogStyle);
            builder.setMessage("ထွက်မှာသေချာပြီလား !");

            builder.setPositiveButton("ထွက်မယ်", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    System.exit(0);
                }
            });

            builder.setNegativeButton("မထွက်သေးပါ", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
            builder.show();

        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(),"Home");
        adapter.addFragment(new CategoriesFragment(),"Categories");
        adapter.addFragment(new SearchFragment(),"Search");
        adapter.addFragment(new ShoppingListFragment(),"Shopping List");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }



        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }



}
