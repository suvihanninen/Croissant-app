package com.example.croissanapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String storeTitlesArray[];
    String reviewsArray[];
    int croissantImages[];
    Context context;

    public MyAdapter(Context ct, String[] storeTitle, String[] review, int[] imag) {
        storeTitlesArray = storeTitle;
        reviewsArray = review;
        croissantImages = imag;
        context = ct;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    //this method binds together the data to the UI which the user sees on their screen
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


          holder.storeNameOneRow.setText(storeTitlesArray[position]);
          holder.reviewOneRow.setText(reviewsArray[position]);
          holder.imageOneRow.setImageResource(croissantImages[position]);


          //we have onClickListener which is connected to the root layout which is one_row
          holder.mainLayout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(context, SecondActivity.class);
                  intent.putExtra(context.getString(R.string.store_name_intent_extra), storeTitlesArray[position]);
                  intent.putExtra(context.getString(R.string.review_intent_extra), reviewsArray[position]);
                  intent.putExtra(context.getString(R.string.images_intent_extra), croissantImages[position]);
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return storeTitlesArray.length;
    }

    //here we assign our UI elements to functionalities
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView storeNameOneRow, reviewOneRow;
        ImageView imageOneRow;
        ConstraintLayout mainLayout;

        public MyViewHolder(View itemView){
            super(itemView);
            storeNameOneRow = itemView.findViewById(R.id.store_name_one_row_txt);
            reviewOneRow = itemView.findViewById(R.id.review_one_row_txt);
            imageOneRow = itemView.findViewById(R.id.image_one_row_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
