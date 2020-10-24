package com.birdlogbook.sajja.birdlogbook.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private ArrayList<File> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        //Toast.makeText(this,file.toString(),Toast.LENGTH_SHORT).show();


        //get the dir PBLOgNotePhote1
        File file=this.getDir("PBlogNotePhoto1", Context.MODE_PRIVATE);

        //put to array lite all image in PBogNotePhoto1
        list=imageReader(file);

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

        //create gui
        guiCreate();

    }

    //put all image to array list in pass root
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

    private void viewInMapBtnOnclick(View view){
        Intent intent=new Intent(this,MapGalleryActivity.class);
        startActivity(intent);
    }
    private void guiCreate(){
        gridView=(GridView)findViewById(R.id.gridView);
        viewInMapBtn=(Button)findViewById(R.id.viewInMapBtn);

        viewInMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewInMapBtnOnclick(v);
            }
        });

        gridView.setAdapter(new GridAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(getApplicationContext(),ViewImage.class).putExtra("img",list.get(i).toString()));
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

    class GridAdapter extends  BaseAdapter{

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
            view=getLayoutInflater().inflate(R.layout.single_grid,viewGroup,false);
            ImageView imageView= (ImageView) view.findViewById(R.id.imageView2);
            imageView.setImageURI(Uri.parse(getItem(i).toString()));
            return view;
        }
    }



    //gui define
    GridView gridView;
    Button viewInMapBtn;
    private BottomBar mBottomBar;

}
