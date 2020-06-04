package com.team06.foodbox.Model;

public class Favourite {

    private int favouriteId;
    private int recipeId;
    private String title;
    private String favImage;

    public Favourite() {
    }

    public Favourite(int favouriteId, int recipeId, String title, String favImage) {
        this.favouriteId = favouriteId;
        this.recipeId = recipeId;
        this.title = title;
        this.favImage = favImage;
    }

    public Favourite(int recipeId, String title, String favImage) {
        this.recipeId = recipeId;
        this.title = title;
        this.favImage = favImage;
    }

    public int getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFavImage() {
        return favImage;
    }

    public void setFavImage(String favImage) {
        this.favImage = favImage;
    }
}





