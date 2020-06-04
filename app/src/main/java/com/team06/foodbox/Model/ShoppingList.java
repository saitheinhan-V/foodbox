package com.team06.foodbox.Model;

public class ShoppingList {

    private int id;
    private String title;
    private String ingredient;


    public ShoppingList(String title, String ingredient) {
        this.title = title;
        this.ingredient = ingredient;
    }

    public ShoppingList(int id, String title, String ingredient) {
        this.id = id;
        this.title = title;
        this.ingredient = ingredient;
    }

    public ShoppingList() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
