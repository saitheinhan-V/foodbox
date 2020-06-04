package com.team06.foodbox.Model;

public class CategoriesList {

    private int recipeId;
    private int categoryId;
    private String recipeTitle;
    private String recipeImage;
    private String recipeIngredient;
    private String recipeDirection;

    public CategoriesList(int recipeId, int categoryId, String recipeTitle, String recipeImage, String recipeIngredient, String recipeDirection) {
        this.recipeId = recipeId;
        this.categoryId = categoryId;
        this.recipeTitle = recipeTitle;
        this.recipeImage = recipeImage;
        this.recipeIngredient = recipeIngredient;
        this.recipeDirection = recipeDirection;
    }

    public CategoriesList() {
    }

    public CategoriesList(int categoryId, String recipeTitle, String recipeImage, String recipeIngredient, String recipeDirection) {
        this.categoryId = categoryId;
        this.recipeTitle = recipeTitle;
        this.recipeImage = recipeImage;
        this.recipeIngredient = recipeIngredient;
        this.recipeDirection = recipeDirection;
    }


    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(String recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    public String getRecipeDirection() {
        return recipeDirection;
    }

    public void setRecipeDirection(String recipeDirection) {
        this.recipeDirection = recipeDirection;
    }
}
