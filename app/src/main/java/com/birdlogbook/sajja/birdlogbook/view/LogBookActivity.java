package com.birdlogbook.sajja.birdlogbook.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.birdlogbook.sajja.birdlogbook.controller.ImageController;
import com.birdlogbook.sajja.birdlogbook.controller.LogNoteController;
import com.birdlogbook.sajja.birdlogbook.model.Image;
import com.birdlogbook.sajja.birdlogbook.model.LogNote;
import com.birdlogbook.sajja.birdlogbook.validation.SpinnerListCreate;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogBookActivity extends AppCompatActivity {
    private LogNoteController logNoteController=null;
    private ImageController imageController=null;
    private LogNote logNote=null;
    private Image image=null;
    private ArrayList<LogNote> logNotesList;
    private ArrayList<File> list;
    private ArrayList<Image> allDetails;
    private File file=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_log_book);

        logNoteController=new LogNoteController(this);
        imageController=new ImageController(this);
        image=new Image();



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

       // Toast.makeText(this,String.valueOf(list.size()),Toast.LENGTH_LONG).show();
        guiCreate();

        //set serchby Spinner
        List<String> searchByList = SpinnerListCreate.setSearchBy();
        ArrayAdapter<String> seachByArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,searchByList);
        seachByArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        searchSpinner1.setAdapter(seachByArrayAdapter);

        //default view all log Notes
        allDetails = imageController.getAllDetails();

        file=this.getDir("PBlogNotePhoto1", Context.MODE_PRIVATE);
        list=imageReader(file);
        listView.setAdapter(new GridAdapter());

    }

    private void searchSpinner_2_OnTouch(View view){
        String selectItem = searchSpinner1.getSelectedItem().toString();
        if (selectItem.equals("SEARCH")){

        }
        else if(selectItem=="date"){



        }else if(selectItem=="Province") {

           String selectedItem = (String) searchSpinner2.getSelectedItem();
            if (file!=null && logNoteController!=null){
                ArrayList<String> pList = logNoteController.getAllIIDdByProvince(selectedItem);
                list = imageReaderFilter(file, pList);
                listView.setAdapter(new GridAdapter());
            }





        }
        else if(selectItem=="City") {

        }
        else if(selectItem=="Bird Name") {

            String selectedItem = (String) searchSpinner2.getSelectedItem();
            if (file!=null){
                ArrayList<String> birdList = logNoteController.getAll_IIDdByname(selectedItem);

                list = imageReaderFilter(file, birdList);
                listView.setAdapter(new GridAdapter());
            }


        }
        else if(selectItem=="Habitat") {


            String selectedItem = (String) searchSpinner2.getSelectedItem();
            if (file!=null){
                ArrayList<String> habitatList = logNoteController.getAll_IIDdByHabitat(selectedItem);

                list = imageReaderFilter(file, habitatList);
                listView.setAdapter(new GridAdapter());
            }


        }
        else if(selectItem=="Size") {
            String selectedItem = (String) searchSpinner2.getSelectedItem();
            if (file!=null){
                ArrayList<String> sizeList = logNoteController.getAll_IIDdBySize(selectedItem);

                list = imageReaderFilter(file, sizeList);
                listView.setAdapter(new GridAdapter());
            }

        }
        else if(selectItem=="Shape") {
            String selectedItem = (String) searchSpinner2.getSelectedItem();
            if (file!=null){
                ArrayList<String> shapeList = logNoteController.getAll_IIDdByShape(selectedItem);

                list = imageReaderFilter(file, shapeList);
                listView.setAdapter(new GridAdapter());
            }

        }else if(selectItem=="Looks Like"){
            String selectedItem = (String) searchSpinner2.getSelectedItem();
            if (file!=null){
                ArrayList<String> looksLikeList = logNoteController.getAll_IIDdByLooksLike(selectedItem);

                list = imageReaderFilter(file, looksLikeList);
                listView.setAdapter(new GridAdapter());
            }
        }
    }
    private void searchSpinner_1_OnTouch(View view){
        String selectItem = searchSpinner1.getSelectedItem().toString();
        if (selectItem.equals("SEARCH")){

        }
        else if(selectItem=="Size"){
            //set size list
            List<String> sizeList  = SpinnerListCreate.setSizeSpinner();
            ArrayAdapter<String> sizeArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sizeList);
            sizeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            searchSpinner2.setAdapter(sizeArrayAdapter);



        }else if(selectItem=="Province") {
            //set province list
            List<String> provinceList = SpinnerListCreate.setProvinceSpinner();
            ArrayAdapter<String> provincrArrayAdapter=new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,provinceList);
            provincrArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_item);
            searchSpinner2.setAdapter(provincrArrayAdapter);


        }
        else if(selectItem=="City") {

        }
        else if(selectItem=="Bird Name") {

           ArrayList<String> allBirdName = logNoteController.getAllBirdName();

            ArrayAdapter<String> birdArrayAdapter=new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item,allBirdName);
            birdArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_item);
            searchSpinner2.setAdapter(birdArrayAdapter);

        }
        else if(selectItem=="Habitat") {
            //set Habitat list
            List<String> habitatList = SpinnerListCreate.setHabitatSpinner();
            ArrayAdapter<String> habitatArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,habitatList);
            habitatArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            searchSpinner2.setAdapter(habitatArrayAdapter);
        }
        else if(selectItem=="Shape") {
            //set shape list
            List<String> shapeList  = SpinnerListCreate.setShapeSpinner();
            ArrayAdapter<String> shapeArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,shapeList);
            shapeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            searchSpinner2.setAdapter(shapeArrayAdapter);
        }else if(selectItem=="Looks Like"){
            //set look like list
            List<String> lookLikeList = SpinnerListCreate.setLookLikeSpinner();
            ArrayAdapter<String> lookLikeArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lookLikeList);
            lookLikeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            searchSpinner2.setAdapter(lookLikeArrayAdapter);
        }
    }
    ArrayList<File> imageReader(File root){
        ArrayList<File> a=new ArrayList<>();
        File[] files=root.listFiles();
        for (int i=0;i<files.length;i++){
            if (files[i].isDirectory()){
                a.addAll(imageReader(files[i]));

            }else {
                if (files[i].getName().endsWith(".jpeg")){
                    a.add(files[i]);
                }
            }
        }
        return a;
    }
    ArrayList<File> imageReaderFilter(File root,ArrayList<String> imageNames){
        ArrayList<File> a=new ArrayList<>();
        File[] files=root.listFiles();
        for (int i=0;i<files.length;i++){
            if (files[i].isDirectory()){
                a.addAll(imageReader(files[i]));

            }else {
                if (files[i].getName().endsWith(".jpeg")){
                    if (imageNames.contains(files[i].getName())){
                        a.add(files[i]);
                    }

                }
            }
        }
        return a;
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

       // finish();
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


    private void guiCreate(){

        searchSpinner1=(Spinner)findViewById(R.id.searchBySpinner);

        searchSpinner2=(Spinner) findViewById(R.id.searchSpinner);

        imageView=(ImageView)findViewById(R.id.logBookImageView);

        textView1=(TextView)findViewById(R.id.logBookTextView1);
        textView2=(TextView)findViewById(R.id.logBookTextView2);
        textView3=(TextView)findViewById(R.id.logBookTextView3);

        listView=(ListView)findViewById(R.id.logBookGridView);
        //listView.setAdapter(new GridAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),ViewLogNote.class).putExtra("img",list.get(i).toString()));
            }
        });
       searchSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchSpinner_1_OnTouch(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        searchSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchSpinner_2_OnTouch(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       ////////////
        // Customize the colors here
        // Tab Item Alpha







    }
    class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=getLayoutInflater().inflate(R.layout.single_log_book_grid,viewGroup,false);
            ImageView imageView= (ImageView) view.findViewById(R.id.logBookImageView);
            TextView textView1=(TextView)view.findViewById(R.id.logBookTextView1) ;
            TextView textView2=(TextView)view.findViewById(R.id.logBookTextView2) ;
            TextView textView3=(TextView)view.findViewById(R.id.logBookTextView3) ;
            Image image = allDetails.get(i);
            textView1.setText(image.getDate());
            textView2.setText(image.getTime());
            textView3.setText(image.getxI()+" , "+image.getyI());
            imageView.setImageURI(Uri.parse(getItem(i).toString()));
            return view;
        }
    }

    //gui define
    private EditText searchText;
    private Spinner searchSpinner1;
    private Spinner searchSpinner2;
    private Button searchButton;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private ImageView imageView;

    private ListView listView;

    private BottomBar mBottomBar;

}
