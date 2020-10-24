package com.birdlogbook.sajja.birdlogbook.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.birdlogbook.sajja.birdlogbook.controller.ImageController;
import com.birdlogbook.sajja.birdlogbook.db.BackGroundWorker;
import com.birdlogbook.sajja.birdlogbook.model.Image;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.io.File;

public class ViewImage extends AppCompatActivity {
    private  ImageController imageController=null;
    private Image image=null;
    private String imageUri=null;
    String imagePath=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        ///navi bar***
        mBottomBar = BottomBar.attach(this, savedInstanceState,
                Color.parseColor("#E8F5E9"), // Background Color
                ContextCompat.getColor(this, R.color.buttonColor), // Tab Item Color
                0.25f);

        mBottomBar.setItems(R.menu.bottom_bar_menu);



        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {

            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                startActivites(menuItemId, false);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                //startActivites(menuItemId, true);
            }
        });
        mBottomBar.setId(R.id.gallery);
        ///****


        guiCreate();
        //Intent i=getIntent();
        //File f=i.getExtras().getParcelable("img");
        imageUri=getIntent().getStringExtra("img");
        imageView.setImageURI(Uri.parse(imageUri));

        //imagePath=imageUri.g


        //set image details
        try {
           imageController=new ImageController(this);
            image=new Image();

            //get image name from image uri
            String[] split = imageUri.split("/");

              // Toast.makeText(this, split[5],Toast.LENGTH_LONG).show();
               image.setphotoDir(split[5]);


            image = imageController.getAllByPhotoDir(image);

         if (image!=null){
               // Toast.makeText(this,String.valueOf(image.getIID()),Toast.LENGTH_SHORT).show();
             lxView.setText(String.valueOf(image.getxI()));
                lyView.setText(String.valueOf( image.getyI()));
                dateView.setText(image.getDate());
                timeView.setText(image.getTime());
            }


        }finally {
         //   if (imageController!=null){
           //     imageController.close();
           // }
           // if (image!=null){

//            }
       }



    }
    private void shareLogNote(){


    }
    private void guiCreate(){
        imageView=(ImageView)findViewById(R.id.imageView3);

        lxView=(TextView)findViewById(R.id.lxView);
        lyView=(TextView)findViewById(R.id.lyView);
        dateView=(TextView) findViewById(R.id.dateView);
        timeView=(TextView) findViewById(R.id.timeView);

    }
    private void cameraButtonOnClick(){
        //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // startActivityForResult(intent, request_image_capture );

        Intent intent=new Intent(this,CameraActivity.class);
        startActivity(intent);

    }
    private void newLogNoteButtonOnClick(){
        Intent intent=new Intent(this,NewLogNoteActivity.class);
        startActivity(intent);

    }
    private void galleryButtonOnClick(){
        Intent intent=new Intent(this,GalleryActivity.class);
        startActivity(intent);

    }
    private void logBookButtonOnClick(){

        Intent intent=new Intent(this,LogBookActivity.class);
        startActivity(intent);

    }
    private void mapButtonOnclick(){
        Intent intent=new Intent(this,GoogleMapsActivity.class);
        startActivity(intent);
    }
    private void logOutButtonOnClick(){

        finish();
        startActivity(new Intent(this,LogInActivity.class));
    }
    private void startActivites(int menuId, boolean isReselected) {
        if (menuId == R.id.gallery) {
            galleryButtonOnClick();
        } else if (menuId == R.id.home) {
            //logBookButtonOnClick();

        } else if (menuId == R.id.logNote) {
            newLogNoteButtonOnClick();

        } else if (menuId == R.id.camera) {
            cameraButtonOnClick();
        } else if (menuId == R.id.mapM) {
            mapButtonOnclick();
        }else if (menuId == R.id.logout) {
            logOutButtonOnClick();
        }
    }


    //gui define
    private ImageView imageView;

    private TextView lxView;
    private TextView lyView;
    private TextView dateView;
    private TextView timeView;

    private BottomBar mBottomBar;



}
