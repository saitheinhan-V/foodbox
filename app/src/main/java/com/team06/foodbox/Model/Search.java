package com.team06.foodbox.Model;

public class Search {

    private String searchId;
    private String searchTitle;
    private String searchImage;

    public Search() {
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchImage() {
        return searchImage;
    }

    public void setSearchImage(String searchImage) {
        this.searchImage = searchImage;
    }

    public Search(String searchId, String searchTitle, String searchImage) {

        this.searchId = searchId;
        this.searchTitle = searchTitle;
        this.searchImage = searchImage;
    }
}
