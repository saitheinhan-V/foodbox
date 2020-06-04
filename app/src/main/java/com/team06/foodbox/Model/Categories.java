package com.team06.foodbox.Model;

public class Categories {

    private int categoriesId;
    private String categoriesTitle;
    private String categoriesImage;


    public Categories(int categoriesId, String categoriesTitle, String categoriesImage) {
        this.categoriesId = categoriesId;
        this.categoriesTitle = categoriesTitle;
        this.categoriesImage = categoriesImage;
    }

    public Categories() {
    }

    public Categories(String categoriesTitle, String categoriesImage) {
        this.categoriesTitle = categoriesTitle;
        this.categoriesImage = categoriesImage;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesTitle() {
        return categoriesTitle;
    }

    public void setCategoriesTitle(String categoriesTitle) {
        this.categoriesTitle = categoriesTitle;
    }

    public String getCategoriesImage() {
        return categoriesImage;
    }

    public void setCategoriesImage(String categoriesImage) {
        this.categoriesImage = categoriesImage;
    }
}
