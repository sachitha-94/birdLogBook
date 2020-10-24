package com.birdlogbook.sajja.birdlogbook.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.birdlogbook.sajja.birdlogbook.controller.ImageController;
import com.birdlogbook.sajja.birdlogbook.controller.ImageSaver;
import com.birdlogbook.sajja.birdlogbook.controller.LogNoteController;
import com.birdlogbook.sajja.birdlogbook.controller.SystemInformationController;
import com.birdlogbook.sajja.birdlogbook.model.Image;
import com.birdlogbook.sajja.birdlogbook.model.LogNote;
import com.birdlogbook.sajja.birdlogbook.validation.CustomAdapter;
import com.birdlogbook.sajja.birdlogbook.validation.SpinnerListCreate;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class  NewLogNoteActivity extends AppCompatActivity {
    static final int request_image_capture=1;
    private ImageSaver imageSaver=null;
    private  Bitmap photo=null;

    private Image image;
    private LogNote logNote;

    private  ImageController ic;
    private LogNoteController lnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log_note);

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
       /* String[] shapes={"qw","qwe"};
        int flag[]={R.drawable.sh_babbler,R.drawable.sh_cormorant};
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),flag,shapes);
        shapeSpinner.setAdapter(customAdapter);*/

        //set size list
        List<String> sizeList  = SpinnerListCreate.setSizeSpinner();
        ArrayAdapter<String> sizeArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sizeList);
        sizeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sizeSpinner.setAdapter(sizeArrayAdapter);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==request_image_capture &&resultCode==RESULT_OK){
            //String data1 =data.getData().toString();
           // Toast.makeText(this,data1,Toast.LENGTH_SHORT).show();
            Bundle extras=data.getExtras();
            photo = (Bitmap) extras.get("data");
            newLogNoteImageView.setImageBitmap(photo);

        }
    }

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
    private void saveButtonOnClick(View view){
        try {

            image = new Image();
            logNote = new LogNote();


            ic= new ImageController(this);
            lnc = new LogNoteController(this);

            imageSaver=new ImageSaver(this);
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


            //get system date and time
            SystemInformationController sic =new SystemInformationController();

            String date = sic.getCurrentDate();
            String time = sic.getCurrentTime();

            //get current location
            GoogleMapsActivity googleMapsActivity=new GoogleMapsActivity();

            Location mLastLocation = googleMapsActivity.mLastLocation;

           // Toast.makeText(this, (int) mLastLocation.getLatitude(),Toast.LENGTH_SHORT).show();



            //get last image id in data base
            int lastImageID  =ic.getLastImageID()+1;

            //save photo in phone memory
            String imgName=lastImageID+".jpeg";
            imageSaver.setDirectoryName("PBlogNotePhoto1");
            imageSaver.setFileName(imgName).save(photo);

            imageSaver.getUri();

            //set image details to Image model
            image.setxI(Double.valueOf(lon));
            image.setyI(Double.valueOf(lat));
            image.setDate(date);
            image.setTime(time);
            image.setphotoDir(imgName);

            //add image to to database..
            boolean imgRes = ic.addImage(image);

            //set lognot details to lognote Model
            logNote.setiID(lastImageID);
            logNote.setsID(Integer.valueOf(sID));
            logNote.setDate(date);
            logNote.setTime(time);
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


           //add lognote to database
            boolean logNoteRes = lnc.addLogNote(logNote);

            if (imgRes && logNoteRes) {
                Toast.makeText(this, "Added to library", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainMenuActivity.class));
                //Toast.makeText(this,logNote.toString(),Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "cannot add to library", Toast.LENGTH_SHORT).show();
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

        newLogNoteImageView=(ImageView)findViewById(R.id.logNoteimageView);

        savebutton=(Button)findViewById(R.id.saveLogNoteButton);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveButtonOnClick(view);
            }
        });

        newLogNoteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, request_image_capture);
                }finally {

                }
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

    private ImageView newLogNoteImageView;

    private Button savebutton;

    private BottomBar mBottomBar;

}
