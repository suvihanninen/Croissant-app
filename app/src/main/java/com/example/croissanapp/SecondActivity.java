package com.example.croissanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Intent intent;

    static String TAG = "SecondActivity";

    ImageView croissantImageView;
    TextView storeNameTextView;
    TextView reviewTextView;

    String storeName, review;
    int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        intent = getIntent();

        storeNameTextView = findViewById(R.id.store_name);
        reviewTextView = findViewById(R.id.review);
        croissantImageView = findViewById(R.id.mainImageView);

        getData();
        setData();

    }

    //here we need two methods: to GET the data which we passed to the intent
    // and then SET the data to our elements on the secondActivity layout/UI
    private void getData(){
        

        //we need to check if there is the data we want inside of the intent. This happens with the if block.
        if(intent.hasExtra(getString(R.string.store_name_intent_extra)) && intent.hasExtra(getString(R.string.store_name_intent_extra)) && intent.hasExtra(getString(R.string.store_name_intent_extra))){

               storeName = getStringFromIntent(R.string.store_name_intent_extra);
               review = getStringFromIntent(R.string.review_intent_extra);
               image = getIntegerFromIntent(R.string.images_intent_extra, 1);

        }else{
            Toast.makeText(this, R.string.no_data_toast, Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        storeNameTextView.setText(storeName);
        reviewTextView.setText((review));
        croissantImageView.setImageResource(image);
    }

    private String getStringFromIntent(int stringID) {
        String stringFromResource = getString(stringID);
        return intent.getStringExtra(stringFromResource);
    }

    private Integer getIntegerFromIntent(int stringID, int val){
        String stringFromResource = getString(stringID);
        return intent.getIntExtra(stringFromResource, val);
    }
    
    
}