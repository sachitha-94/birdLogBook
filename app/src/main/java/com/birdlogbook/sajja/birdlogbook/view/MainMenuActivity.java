package com.birdlogbook.sajja.birdlogbook.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.birdlogbook.sajja.birdlogbook.controller.ImageController;
import com.birdlogbook.sajja.birdlogbook.controller.ImageSaver;
import com.birdlogbook.sajja.birdlogbook.controller.LogNoteController;
import com.birdlogbook.sajja.birdlogbook.controller.SystemInformationController;
import com.birdlogbook.sajja.birdlogbook.model.Image;
import com.birdlogbook.sajja.birdlogbook.model.LogNote;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainMenuActivity extends AppCompatActivity {
    private   ImageSaver imageSaver=null;
    private SystemInformationController sic=null;

    private  Bitmap photo=null;

    private Image image;
    private LogNote logNote;

    private  ImageController ic;
    private LogNoteController lnc;


    static final int request_image_capture=1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        imageSaver=null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mBottomBar = BottomBar.attach(this, savedInstanceState,
                Color.parseColor("#E8F5E9"), // Background Color
                ContextCompat.getColor(this, R.color.buttonColor), // Tab Item Color
                0.25f);
        guiCreate();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==request_image_capture && resultCode==RESULT_OK ) {

            Bundle extra = data.getExtras();
            Bitmap photo = (Bitmap) extra.get("data");


            try {
                ic=new ImageController(this);
                sic =new SystemInformationController();
                imageSaver =new ImageSaver(this);
                imageSaver.setDirectoryName("PBCameraPhoto1");
                image=new Image();

                //get system date and time
                String date = sic.getCurrentDate();
                String time = sic.getCurrentTime();

                //get last image id in data base
                int lastImageID  =ic.getLastImageID()+1;

                //save photo in phone memory
                String imgName=lastImageID+".jpeg";
                imageSaver.setFileName(imgName).save(photo);

                String uri = String.valueOf(imageSaver.getUri());
                Toast.makeText(this,uri,Toast.LENGTH_LONG).show();

                //location
                double lx=1.0;
                double ly=1.0;

                //set image details to Image model
                image.setxI(lx);
                image.setyI(ly);
                image.setDate(date);
                image.setTime(time);
                image.setphotoDir(imgName);

                //add image to to database..
                boolean imgRes = ic.addImage(image);
                if (imgRes){
                    Toast.makeText(this,"photo saved",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"fail to save",Toast.LENGTH_SHORT).show();

                }


            }finally {
                ic.close();

            }

        }
    }

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }



    private void cameraButtonOnClick(){
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
            logBookButtonOnClick();

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

    private void guiCreate() {
       /* cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        newLogNoteButton = (ImageButton) findViewById(R.id.newLogNoteButton);
        galleryButton = (ImageButton) findViewById(R.id.galleryButton);
        logBookButton = (ImageButton) findViewById(R.id.logBookButton);
        mapButton = (ImageButton) findViewById(R.id.mapBtn);
        logOutButton = (ImageButton) findViewById(R.id.logOutBtn);*/

      /*  cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraButtonOnClick(view);
            }
        });

        newLogNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newLogNoteButtonOnClick(view);
            }
        });
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryButtonOnClick(view);
            }
        });
        logBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logBookButtonOnClick(view);
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapButtonOnclick();

            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutButtonOnClick(view);
            }
        });*/


        ////////////
        // Customize the colors here
        // Tab Item Alpha
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
    }


    //gui define
    private ImageButton cameraButton;
    private ImageButton newLogNoteButton;
    private ImageButton galleryButton;
    private ImageButton logBookButton;
    private ImageButton mapButton;
    private ImageButton logOutButton;

    private BottomBar mBottomBar;
}
