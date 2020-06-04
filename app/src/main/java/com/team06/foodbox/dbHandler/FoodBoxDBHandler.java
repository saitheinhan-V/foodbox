package com.team06.foodbox.dbHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.team06.foodbox.Model.Categories;
import com.team06.foodbox.Model.CategoriesList;
import com.team06.foodbox.Model.Favourite;
import com.team06.foodbox.Model.Home;
import com.team06.foodbox.Model.Ingredient;
import com.team06.foodbox.Model.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class FoodBoxDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "FoodBoxDB.db";

    //Table name
    private static final String CATEGORY_TABLE = "category";
    private static final String RECIPE_TABLE = "recipe";
    private static final String INGREDIENT_TABLE = "ingredient";
    private static final String FAVOURITE_TABLE = "favourite";
    private static final String METHOD_TABLE = "method";
    private static final String SHOPPING_TABLE = "shopping";

    //category table column name
    private static final String KEY_ID = "category_id";
    private static final String KEY_TITLE = "category_title";
    private static final String KEY_IMAGE = "category_image";

    //recipe table column name
    private static final String RECIPE_ID = "recipe_id";
    private static final String RECIPE_TITLE = "recipe_title";
    private static final String RECIPE_INGREDIENT = "recipe_ingredient";
    private static final String RECIPE_DIRECTION = "recipe_direction";
    private static final String RECIPE_IMAGE = "recipe_image";

    //ingredient table column name
    private static final String INGREDIENT_ID = "ingredient_id";
    private static final String INGREDIENT_NAME = "ingredient_name";

    //favourite table column name
    private static final String FAVOURITE_ID="favourite_id";

    //method table column name
    private static final String METHOD_ID = "method_id";
    private static final String METHOD_TITLE = "method_title";
    private static final String METHOD_DESCRIPTION = "method_description";
    private static final String METHOD_IMAGE = "method_image";

    //shopping table column name
    private static final String  SHOPPING_ID = "shopping_id";

    public FoodBoxDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_CATEGORY = " CREATE TABLE " + CATEGORY_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT ,"
                + KEY_IMAGE + " TEXT )";

        String CREATE_TABLE_RECIPE = " CREATE TABLE " + RECIPE_TABLE + "("
                + RECIPE_ID + " INTEGER PRIMARY KEY,"
                + KEY_ID + " INTEGER, "
                + RECIPE_TITLE + " TEXT ,"
                + RECIPE_IMAGE + " TEXT ,"
                + RECIPE_INGREDIENT + " TEXT ,"
                + RECIPE_DIRECTION + " TEXT )";

        String CREATE_TABLE_INGREDIENT = " CREATE TABLE " + INGREDIENT_TABLE + "("
                + INGREDIENT_ID + " INTEGER PRIMARY KEY ,"
                + INGREDIENT_NAME + " TEXT )";

        String CREATE_TABLE_FAVOURITE = " CREATE TABLE " + FAVOURITE_TABLE + "("
                + FAVOURITE_ID + " INTEGER PRIMARY KEY ,"
                + RECIPE_ID + " INTEGER, "
                + RECIPE_TITLE + " TEXT, "
                + RECIPE_IMAGE + " TEXT ) ";

        String CREATE_TABLE_METHOD = " CREATE TABLE " + METHOD_TABLE + "("
                + METHOD_ID + " INTEGER PRIMARY KEY ,"
                + METHOD_TITLE + " TEXT, "
                + METHOD_DESCRIPTION + " TEXT, "
                + METHOD_IMAGE + " TEXT ) ";

        String CREATE_TABLE_SHOPPING = " CREATE TABLE " + SHOPPING_TABLE + "("
                + SHOPPING_ID + " INTEGER PRIMARY KEY ,"
                + RECIPE_TITLE + " TEXT, "
                + RECIPE_INGREDIENT + " TEXT )";


        //execute table
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_INGREDIENT);
        db.execSQL(CREATE_TABLE_FAVOURITE);
        db.execSQL(CREATE_TABLE_METHOD);
        db.execSQL(CREATE_TABLE_SHOPPING);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("  DROP TABLE IF EXISTS  " + CATEGORY_TABLE);
        onCreate(db);

        db.execSQL("  DROP TABLE IF EXISTS  " + RECIPE_TABLE);
        onCreate(db);

        db.execSQL("  DROP TABLE IF EXISTS  " + INGREDIENT_TABLE);
        onCreate(db);

        db.execSQL("  DROP TABLE IF EXISTS  " + FAVOURITE_TABLE);
        onCreate(db);

        db.execSQL("  DROP TABLE IF EXISTS  " + METHOD_TABLE);
        onCreate(db);

        db.execSQL("  DROP TABLE IF EXISTS  " + SHOPPING_TABLE);
        onCreate(db);
    }

    //insert category from json to database
    public void addCategory(Categories categories) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,categories.getCategoriesId());
        values.put(KEY_TITLE, categories.getCategoriesTitle());
        values.put(KEY_IMAGE, categories.getCategoriesImage());

        // Inserting Row
        db.insert(CATEGORY_TABLE, null, values);
    }


    //get all categories list
    public List<Categories> getAllCategories() {
        List<Categories> categoriesList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + CATEGORY_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Categories categories = new Categories();

                categories.setCategoriesId(cursor.getInt(0));
                categories.setCategoriesTitle(cursor.getString(1));
                categories.setCategoriesImage(cursor.getString(2));

                categoriesList.add(categories);

            } while (cursor.moveToNext());

            cursor.close();

        }
        return categoriesList;
    }

    //get all recipe list by category id
    public List<CategoriesList> getAllRecipe(int id) {
        List<CategoriesList> categoriesListList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + RECIPE_TABLE + " WHERE " + KEY_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CategoriesList categoriesList = new CategoriesList();

                categoriesList.setRecipeId(cursor.getInt(0));
                categoriesList.setCategoryId(cursor.getInt(1));
                categoriesList.setRecipeTitle(cursor.getString(2));
                categoriesList.setRecipeImage(cursor.getString(3));
                categoriesList.setRecipeIngredient(cursor.getString(4));
                categoriesList.setRecipeDirection(cursor.getString(5));

                categoriesListList.add(categoriesList);

            } while (cursor.moveToNext());

            cursor.close();

        }
        return categoriesListList;
    }



    //get all recipe details by recipe id
    public List<CategoriesList> getAllRecipeDetails(int id) {
        List<CategoriesList> categoriesListList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + RECIPE_TABLE + " WHERE " + RECIPE_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CategoriesList categoriesList = new CategoriesList();

                categoriesList.setRecipeId(cursor.getInt(0));
                categoriesList.setCategoryId(cursor.getInt(1));
                categoriesList.setRecipeTitle(cursor.getString(2));
                categoriesList.setRecipeImage(cursor.getString(3));
                categoriesList.setRecipeIngredient(cursor.getString(4));
                categoriesList.setRecipeDirection(cursor.getString(5));

                categoriesListList.add(categoriesList);

            } while (cursor.moveToNext());

            cursor.close();

        }
        return categoriesListList;
    }



    //insert recipe list from json to database
    public void addRecipe(CategoriesList categoriesList) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RECIPE_ID, categoriesList.getRecipeId());
        values.put(KEY_ID, categoriesList.getCategoryId());
        values.put(RECIPE_TITLE, categoriesList.getRecipeTitle());
        values.put(RECIPE_IMAGE, categoriesList.getRecipeImage());
        values.put(RECIPE_INGREDIENT, categoriesList.getRecipeIngredient());
        values.put(RECIPE_DIRECTION, categoriesList.getRecipeDirection());

        db.insert(RECIPE_TABLE, null, values);
    }

    //get recipe ingredient
    public List<CategoriesList> getRecipeIngredient(){

        List<CategoriesList> categoriesLists = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + RECIPE_TABLE ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CategoriesList categoriesList = new CategoriesList();

                categoriesList.setRecipeId(cursor.getInt(0));
                categoriesList.setCategoryId(cursor.getInt(1));
                categoriesList.setRecipeTitle(cursor.getString(2));
                categoriesList.setRecipeImage(cursor.getString(3));
                categoriesList.setRecipeIngredient(cursor.getString(4));
                categoriesList.setRecipeDirection(cursor.getString(5));

                categoriesLists.add(categoriesList);

            } while (cursor.moveToNext());

            cursor.close();

        }
        return categoriesLists;

    }

    public void addFavourite(Favourite favourite) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RECIPE_ID,favourite.getRecipeId());
        values.put(RECIPE_TITLE, favourite.getTitle());
        values.put(RECIPE_IMAGE, favourite.getFavImage());

        db.insert(FAVOURITE_TABLE, null, values);
    }

    //get recipe title and image
    public List<Favourite> getAllFavourite(){

        List<Favourite> favouriteList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + FAVOURITE_TABLE ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Favourite f=new Favourite();

                f.setFavouriteId(Integer.parseInt(cursor.getString(0)));
                f.setRecipeId(cursor.getInt(1));
                f.setTitle(cursor.getString(2));
                f.setFavImage((cursor.getString(3)));


                favouriteList.add(f);

            } while (cursor.moveToNext());

            cursor.close();

        }
        return favouriteList;
    }

    public void deleteFavourite(int recipe_id) {

        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(FAVOURITE_TABLE, RECIPE_ID + " =?" , new String[]{String.valueOf(recipe_id)} );

    }

    public void deleteShopList(String shop_title) {

        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(SHOPPING_TABLE, RECIPE_TITLE + " =?" , new String[]{String.valueOf(shop_title)} );

    }

    public void addIngredient(Ingredient ingredient) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INGREDIENT_ID, ingredient.getId());
        values.put(INGREDIENT_NAME, ingredient.getName());

        db.insert(INGREDIENT_TABLE, null, values);
    }

    //get all ingredient
    public List<Ingredient> getIngredient(){

        List<Ingredient> ingredientList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + INGREDIENT_TABLE ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                Ingredient ingredient=new Ingredient();
                ingredient.setId(cursor.getInt(0));
                ingredient.setName(cursor.getString(1));

                Log.d("IN",cursor.getString(1));

                ingredientList.add(ingredient);
            }while (cursor.moveToNext());

            cursor.close();
        }


        return  ingredientList;
    }



    public void addMethod(Home home) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(METHOD_ID, home.getId());
        values.put(METHOD_TITLE, home.getTitle());
        values.put(METHOD_DESCRIPTION, home.getDescription());
        values.put(METHOD_IMAGE, home.getImage());

        db.insert(METHOD_TABLE, null, values);
    }

    //get all method
    public List<Home> getAllMethod(){

        List<Home> homeList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + METHOD_TABLE ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Home home=new Home();

                home.setId(cursor.getInt(0));
                home.setTitle(cursor.getString(1));
                home.setDescription(cursor.getString(2));
                home.setImage((cursor.getString(3)));


                homeList.add(home);

            } while (cursor.moveToNext());

            cursor.close();

        }
        return homeList;
    }

    //insert into shopping table
    public void addShopping(ShoppingList shoppingList) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(RECIPE_TITLE, shoppingList.getTitle());
        values.put(RECIPE_INGREDIENT, shoppingList.getIngredient());

        db.insert(SHOPPING_TABLE, null, values);
    }

    //get all shopping
    public List<ShoppingList> getAllShopping(){

        List<ShoppingList> shoppingLists = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + SHOPPING_TABLE ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ShoppingList s=new ShoppingList();

                s.setId(cursor.getInt(0));
                s.setTitle(cursor.getString(1));
                s.setIngredient(cursor.getString(2));

                shoppingLists.add(s);

            } while (cursor.moveToNext());

            cursor.close();

        }
        return shoppingLists;
    }

}
