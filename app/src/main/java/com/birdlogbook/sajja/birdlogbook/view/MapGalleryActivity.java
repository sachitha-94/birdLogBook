package com.birdlogbook.sajja.birdlogbook.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.birdlogbook.sajja.birdlogbook.controller.ImageController;
import com.birdlogbook.sajja.birdlogbook.controller.LogNoteController;
import com.birdlogbook.sajja.birdlogbook.model.Image;
import com.birdlogbook.sajja.birdlogbook.model.LogNote;
import com.birdlogbook.sajja.birdlogbook.validation.SpinnerListCreate;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class MapGalleryActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageController ic = null;
    private LogNoteController lnc = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_gallery);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

        ic = new ImageController(this);
        lnc = new LogNoteController(this);


        //set shape list
        List<String> shapeList = SpinnerListCreate.setShapeSpinner();
        ArrayAdapter<String> shapeArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shapeList);
        shapeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        shapeSpinner.setAdapter(shapeArrayAdapter);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //load sri lanka
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(7.5, 80.7), 7.0f));


        //set Allposition
        setAllposition();
       // mMap.clear();


    }

    private void setAllposition() {
        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();

    }

    private void sheapeSpinnerOnclick() {
        mMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        String selectedShape = (String) shapeSpinner.getSelectedItem();
        System.out.println(selectedShape);
        if (selectedShape.equals("")) {
            //get all positions from image table
            ArrayList<LatLng> allPositions = ic.getAllPositions();
            for (LatLng latLng : allPositions) {
                markerOptions.position(latLng);
                mMap.addMarker(markerOptions);
            }
        }else{
            ArrayList<LatLng> allPositionsByshape = lnc.getAllPositionsByshape(Integer.valueOf(selectedShape));
            if (allPositionsByshape != null) {
                for (LatLng latLng : allPositionsByshape) {
                    markerOptions.position(latLng);
                    mMap.addMarker(markerOptions);
                }
            }

        }

    }

    private void guiCreate() {
        shapeSpinner = (Spinner) findViewById(R.id.mapShapeSpinner);
        //allPositionButton = (Button) findViewById(R.id.allPositionButton);

      /*  allPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllposition();
            }
        });*/

        shapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sheapeSpinnerOnclick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MapGallery Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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

    private BottomBar mBottomBar;
    private Spinner shapeSpinner;
    private Button allPositionButton;

}
