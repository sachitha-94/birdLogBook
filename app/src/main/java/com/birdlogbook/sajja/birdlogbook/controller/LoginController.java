package com.birdlogbook.sajja.birdlogbook.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.birdlogbook.sajja.birdlogbook.db.DataBaseWrapper;
import com.birdlogbook.sajja.birdlogbook.model.Image;
import com.birdlogbook.sajja.birdlogbook.model.LogNote;

/**
 * Created by sajja on 10/17/2016.
 */
public class LoginController {
    private static DataBaseWrapper dataBaseWrapper;
    private static SQLiteDatabase sqLiteDatabase;
    private static Context mContext;


    public LoginController(Context context){
        this.mContext=context;
        dataBaseWrapper=DataBaseWrapper.getHelper(mContext);

    }




    public static void openWritable(){
        if (sqLiteDatabase==null){
            dataBaseWrapper=DataBaseWrapper.getHelper(mContext);
        }
        sqLiteDatabase=dataBaseWrapper.getWritableDatabase();

    }
    public static void openReadable(){
        if (sqLiteDatabase==null){
            dataBaseWrapper=DataBaseWrapper.getHelper(mContext);
        }
        sqLiteDatabase=dataBaseWrapper.getReadableDatabase();

    }
    public void close(){
        sqLiteDatabase.close();
    }

    public static boolean upDateLogNote(int log){
        openWritable();
        try {

            Cursor cursor = sqLiteDatabase.rawQuery("UPDATE login SET log = '" + log + "'", null);
            if (cursor.getCount() > 0) {
                return true;
            }
            return false;
        }finally {

        }

    }


}
