package com.birdlogbook.sajja.birdlogbook.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.birdlogbook.sajja.birdlogbook.controller.ImageController;
import com.birdlogbook.sajja.birdlogbook.controller.ImageSaver;
import com.birdlogbook.sajja.birdlogbook.controller.LogNoteController;
import com.birdlogbook.sajja.birdlogbook.controller.SystemInformationController;
import com.birdlogbook.sajja.birdlogbook.db.BackGroundWorker;
import com.birdlogbook.sajja.birdlogbook.model.Image;
import com.birdlogbook.sajja.birdlogbook.model.LogNote;
import com.birdlogbook.sajja.birdlogbook.validation.SpinnerListCreate;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.List;

import static com.birdlogbook.sajja.birdlogbook.R.id.passwordText;

public class ViewLogNote extends AppCompatActivity {

    private Image image;
    private LogNote logNote;

    private  ImageController ic;
    private LogNoteController lnc;
    private  int imageId;
    private Bitmap imageBitmap=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log_note);

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

        //set province list
        List<String> provinceList = SpinnerListCreate.setProvinceSpinner();
        ArrayAdapter<String> provincrArrayAdapter=new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,provinceList);
        provincrArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_item);
        provinceSpinner.setAdapter(provincrArrayAdapter);

        //set Habitat list
        List<String> habitatList = SpinnerListCreate.setHabitatSpinner();
        ArrayAdapter<String> habitatArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,habitatList);
        habitatArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        habitatSpinner.setAdapter(habitatArrayAdapter);

        //set elevation list
        List<String> elevationList = SpinnerListCreate.setElevationSpinner();
        ArrayAdapter<String> elevationArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elevationList);
        elevationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        elevationSpinner.setAdapter(elevationArrayAdapter);

        //set look like list
        List<String> lookLikeList = SpinnerListCreate.setLookLikeSpinner();
        ArrayAdapter<String> lookLikeArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lookLikeList);
        lookLikeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        looksLikeSpinner.setAdapter(lookLikeArrayAdapter);

        //set shape list
        List<String> shapeList  = SpinnerListCreate.setShapeSpinner();
        ArrayAdapter<String> shapeArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,shapeList);
        shapeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        shapeSpinner.setAdapter(shapeArrayAdapter);

        //set size list
        List<String> sizeList  = SpinnerListCreate.setSizeSpinner();
        ArrayAdapter<String> sizeArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sizeList);
        sizeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sizeSpinner.setAdapter(sizeArrayAdapter);

        ///////////set image to image view///////

        String imageUri=getIntent().getStringExtra("img");
        LogNoteImageView.setImageURI(Uri.parse(imageUri));
        imageBitmap=((BitmapDrawable)LogNoteImageView.getDrawable()).getBitmap();


        //set image details
        try {
            ic=new ImageController(this);
            lnc=new LogNoteController(this);
            image=new Image();
            logNote=new LogNote();

            //get image name from image uri
            String[] split = imageUri.split("/");
           // String a=split[5];
            //String[] split1 = a.split(".");

            //Toast.makeText(this, split[5],Toast.LENGTH_LONG).show();
            image.setphotoDir(split[5]);

            image = ic.getAllByPhotoDir(image);
            imageId=image.getIID();

            logNote.setiID(imageId);

            //select * from lognote
            logNote = lnc.searchByIID(logNote);
            if (logNote!=null){
                villageEditText.setText(logNote.getVillage());
                exactLocationEditText.setText(logNote.getExactLocation());
                lonEditText.setText(String.valueOf( logNote.getLon()));
                latEditText.setText(String.valueOf( logNote.getLat()));
                nameEditText.setText(logNote.getName());
                colorEditText.setText(logNote.getColour());
                behaviourEditText.setText(logNote.getBehaviour());
                otherNoteEditText.setText(logNote.getOtherNote());
            }else{
                Toast.makeText(this, "there is no log note",Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(this,logNote.toString(),Toast.LENGTH_LONG).show();

           // shapeSpinner.set;
          /*  provinceSpinner.getSelectedItem().toString();
            nearstCitySpinner.getSelectedItem().toString();
            villageEditText.getText().toString();
             exactLocationEditText.getText().toString();
             elevationSpinner.getSelectedItem().toString();
             habitatSpinner.getSelectedItem().toString();
            looksLikeSpinner.getSelectedItem().toString();
             lonEditText.getText().toString();
             latEditText.getText().toString();
             nameEditText.getText().toString();
             sizeSpinner.getSelectedItem().toString();
             colorEditText.getText().toString();
             behaviourEditText.getText().toString();
             otherNoteEditText.getText().toString();*/


            //Toast.makeText(this, String.valueOf(image.getIID()),Toast.LENGTH_LONG).show();

        }finally {
            //   if (imageController!=null){
            //     imageController.close();
            // }
            // if (image!=null){

//            }
        }

    }
    /*private void onLogIn(){
        if (logNote!=null) {


            String type = "shareLogNote";
            BackGroundWorker backGroundWorker = new BackGroundWorker(this);

            backGroundWorker.execute(type, email, password);
        }

    }*/
    private void provinceSpinnerOnTouch(View view){
        try {

            String province = (String) provinceSpinner.getSelectedItem();
            List<String> cityList = SpinnerListCreate.setCitySpinner(province);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            nearstCitySpinner.setAdapter(arrayAdapter);
        }finally {

        }

    }
    private void onShareLogNote(){
        if (logNote!=null){

            int uid=10;
            int iid=logNote.getiID();
            int sid = logNote.getsID();
            String date = logNote.getDate();
            String time = logNote.getTime();
            String province = logNote.getProvince();
            String nearestCity = logNote.getNearestCity();
            String village = logNote.getVillage();
            String exactLocation = logNote.getExactLocation();
            double lon = logNote.getLon();
            double lat = logNote.getLat();
            String elevation = logNote.getElevation();
            String habitat = logNote.getHabitat();
            String name = logNote.getName();
            String looksLike = logNote.getLooksLike();
            String size = logNote.getSize();
            String colour = logNote.getColour();
            String behaviour = logNote.getBehaviour();
            String otherNote = logNote.getOtherNote();

            String type="shareLogNote";
            BackGroundWorker backGroundWorker=new BackGroundWorker(this);
            backGroundWorker.execute(type,String.valueOf(iid),String.valueOf(sid),date,time,province,nearestCity,village,exactLocation,String.valueOf(lon),String.valueOf(lat),elevation,habitat,name,looksLike,size,colour,behaviour,otherNote,String.valueOf(uid));

        }




    }
    private void upDateButtonOnClick(View view){
        try {

            image = new Image();
            logNote = new LogNote();

            ic= new ImageController(this);
            lnc = new LogNoteController(this);


            String sID = shapeSpinner.getSelectedItem().toString();
            String province = provinceSpinner.getSelectedItem().toString();
            String city = nearstCitySpinner.getSelectedItem().toString();
            String village = villageEditText.getText().toString();
            String exactLocation = exactLocationEditText.getText().toString();
            String elevation = elevationSpinner.getSelectedItem().toString();
            String habitat = habitatSpinner.getSelectedItem().toString();
            String lookLike = looksLikeSpinner.getSelectedItem().toString();
            String lon = lonEditText.getText().toString();
            String lat = latEditText.getText().toString();
            String name = nameEditText.getText().toString();
            String size = sizeSpinner.getSelectedItem().toString();
            String color = colorEditText.getText().toString();
            String behaviour = behaviourEditText.getText().toString();
            String otherNote = otherNoteEditText.getText().toString();

            if (province.equals("")){
                province="empty";
            }
            if (city.equals("")){
                city="empty";
            }
            if (village.equals("")){
                village="empty";
            }
            if (exactLocation.equals("")){
                exactLocation="empty";
            }
            if (elevation.equals("")){
                elevation="0";
            }
            if (habitat.equals("")){
                habitat="empty";
            }
            if (lookLike.equals("")){
                lookLike="empty";
            }
            if (lon.equals("")){
                lon="0";
            }
            if (lat.equals("")){
                lat="0";
            }
            if (name.equals("")){
                name="empty";
            }
            if (sID.equals("")){
                sID="empty";
            }
            if (size.equals("")){
                size="0";
            }
            if (color.equals("")){
                color="empty";
            }
            if (behaviour.equals("")){
                behaviour="empty";
            }
            if (otherNote.equals("")){
                otherNote="empty";
            }




            //set image details to Image model
           // image.setxI(Double.valueOf(lon));
            //image.setyI(Double.valueOf(lat));

            //add image to to database..
           // boolean imgRes = ic.addImage(image);

            //set lognot details to lognote Model
            //logNote.setsID(Integer.valueOf(sID));
            //logNote.setDate(date);
           // logNote.setTime(time);
            logNote.setiID(imageId);
            logNote.setProvince(province);
            logNote.setNearestCity(city);
            logNote.setVillage(village);
            logNote.setExactLocation(exactLocation);
            logNote.setElevation(elevation);
            logNote.setLon(Double.parseDouble(lon));
            logNote.setLat(Double.parseDouble(lat));
            logNote.setHabitat(habitat);
            logNote.setLooksLike(lookLike);
            logNote.setName(name);
            logNote.setSize(size);
            logNote.setColour(color);
            logNote.setBehaviour(behaviour);
            logNote.setOtherNote(otherNote);


            //update lognote
            boolean logNoteRes = lnc.upDateLogNote(logNote);

            if (logNoteRes) {
                Toast.makeText(this, "Updated to library", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainMenuActivity.class));
            } else {
                Toast.makeText(this, "cannot Update to library", Toast.LENGTH_SHORT).show();
            }

        }finally {
            //   ic.close();
            // lnc.close();

        }

    }

    private void guiCreate(){
        provinceSpinner=(Spinner)findViewById(R.id.provinceSpinner);
        nearstCitySpinner=(Spinner)findViewById(R.id.nearestCitySpinner);
        elevationSpinner=(Spinner)findViewById(R.id.elevationSpinner);
        habitatSpinner=(Spinner)findViewById(R.id.habitatSpinner);
        looksLikeSpinner=(Spinner)findViewById(R.id.looksLikeSpinner);
        shapeSpinner=(Spinner)findViewById(R.id.shapeSpinner);
        sizeSpinner=(Spinner) findViewById(R.id.sizeSpinner);

        lonEditText=(EditText)findViewById(R.id.lxText);
        latEditText=(EditText)findViewById(R.id.lyText);
        villageEditText=(EditText)findViewById(R.id.villageText);
        exactLocationEditText=(EditText)findViewById(R.id.exactLocationText);
        nameEditText=(EditText)findViewById(R.id.nameText);
        colorEditText=(EditText)findViewById(R.id.colorText);
        behaviourEditText=(EditText)findViewById(R.id.behaviourText);
        otherNoteEditText=(EditText)findViewById(R.id.otherNoteText);

        LogNoteImageView=(ImageView)findViewById(R.id.logNoteimageView);

        upDateButton=(Button)findViewById(R.id.upDateLogNoteButton);
        shareButton =(Button)findViewById(R.id.shareLogNoteButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareLogNote();
            }
        });


        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                provinceSpinnerOnTouch(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        upDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDateButtonOnClick(v);
            }
        });

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
    private Spinner provinceSpinner;
    private Spinner nearstCitySpinner;
    private Spinner elevationSpinner;
    private Spinner habitatSpinner;
    private Spinner looksLikeSpinner;
    private Spinner shapeSpinner;
    private Spinner sizeSpinner;

    private EditText lonEditText;
    private EditText latEditText;
    private EditText villageEditText;
    private EditText exactLocationEditText;
    private EditText nameEditText;
    private EditText colorEditText;
    private EditText behaviourEditText;
    private EditText otherNoteEditText;

    private ImageView LogNoteImageView;

    private Button upDateButton;
    private Button shareButton;

    private BottomBar mBottomBar;



}
