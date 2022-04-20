package com.example.croissanapp;

import android.content.ContentValues;

import java.util.ArrayList;

public class CroissantDB {
    ArrayList<Object> croissants = new ArrayList<Object>();

    public void addCroissant(String storeName, String review, int image) {
        CroissantObject newCroissant= new CroissantObject(storeName, review, image);
        croissants.add(newCroissant);

    }




}
