package com.example.croissanapp;

public class CroissantObject {
    String storeName;
    String review;
    int image;

    public CroissantObject(String storeName, String review, int image){
        this.storeName = storeName;
        this.review = review;
        this.image = image;

    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}

