package com.example.croissanapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    String s1[], s2[];
    int image[] = {R.drawable.croissant1, R.drawable.croissant2, R.drawable.croissant3, R.drawable.croissant4, R.drawable.croissant5};
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actrivity_main);
        recyclerView = findViewById(R.id.recycler_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        s1 = getResources().getStringArray(R.array.store_name);
        s2 = getResources().getStringArray(R.array.review);

        
        //here we itialize our new class and give all the needed parameters
        MyAdapter myAdapter = new MyAdapter(this, s1, s2, image);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_croissant_button) {
            CustomDialogAddCroissant cdd=new CustomDialogAddCroissant(this);
            cdd.show();
            Toast.makeText(this, "I LOVE CROISSANT", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}