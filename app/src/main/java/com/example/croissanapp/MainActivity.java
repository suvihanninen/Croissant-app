package com.example.croissanapp;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

    public class CustomDialogAddCroissant extends Dialog implements
            android.view.View.OnClickListener {

        private static final int PERMISSION_CODE = 1000;
        private static final int IMAGE_CAPTURE_CODE = 1001;
        Uri image_uri;

        public Activity c;
        public Dialog d;
        public Button addNewCroissant, cancel, captureCroissantPicture, downloadCroissantPicture;
        public EditText storeNameEditText, reviewEditText;
        public ImageView croissanImageView;
        public CroissantDB croissantDB;


        public CustomDialogAddCroissant(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_dialog_add_croissant);
            addNewCroissant = (Button) findViewById(R.id.add_new_croissant_button);
            cancel = (Button) findViewById(R.id.cancel_button);
            storeNameEditText = findViewById(R.id.store_name_edit_text);
            reviewEditText = findViewById(R.id.review_edit_text);
            captureCroissantPicture= findViewById(R.id.capture_croissant_picture_button);
            croissanImageView = findViewById(R.id.croissant_picture_image_view);
            addNewCroissant.setOnClickListener(this);
            cancel.setOnClickListener(this);
            captureCroissantPicture.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_new_croissant_button:
                    String storeName= storeNameEditText.getText().toString().trim();
                    String review= storeNameEditText.getText().toString().trim();
                    int croissantImage = croissanImageView.getImageAlpha();
                    croissantDB.addCroissant(storeName, review, croissantImage);
                    break;
                case R.id.cancel_button:
                    dismiss();
                    break;
                case R.id.capture_croissant_picture_button:
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(ContextCompat.checkSelfPermission(this.c, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(this.c, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED) {
                            //permission not enabled, request it
                            String [] permission = {Manifest.permission. CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            //show popup to request permission
                            c.requestPermissions(permission, PERMISSION_CODE);
                            final String TAG = "MyActivity";
                            Log.i(TAG, "onClick: Open camera porfa");

                        }
                        else{
                            //permission already granted
                            openCamera();
                            String TAG1 = "Open the comera please";
                            Log.i(TAG1, "onClick: Open camera pleaseeeeee");
                        }
                    }
                    else{
                        //system os < marshmellow
                    }
                default:
                    break;
            }
            dismiss();
        }

        private void openCamera(){
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
            image_uri = c.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            //Camera intent
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
            c.startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
        }

        //handling permission result
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
            //this method is called, when user presses Allow or Deny from permission request Popup
            switch(requestCode){
                case PERMISSION_CODE:{
                    if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        //permission from popup was granted
                        openCamera();
                    }else{
                        //permission from popupwas denied
                        Toast.makeText(this.c, "Permission denied", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            //super.onActivityResult(requestCode,resultCode, data);
            //calles when image was captured from camera
            if(resultCode == RESULT_OK){
                //set the image captured to out ImageView
                croissanImageView.setImageURI(image_uri);
            }
        }


    }





}