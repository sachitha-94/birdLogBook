package com.birdlogbook.sajja.birdlogbook.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.birdlogbook.sajja.birdlogbook.db.DataBaseWrapper;
import com.birdlogbook.sajja.birdlogbook.model.LogNote;
import com.birdlogbook.sajja.birdlogbook.view.LogBookActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by sajja on 9/7/2016.
 */
public class LogNoteController {
    private static DataBaseWrapper dataBaseWrapper;
    private static SQLiteDatabase sqLiteDatabase;
    private static Context mContext;


    public LogNoteController(Context context){
        this.mContext=context;
        dataBaseWrapper=DataBaseWrapper.getHelper(mContext);

    }



//change to writable mode
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


//add lognote to lognote table
    public static boolean addLogNote(LogNote logNote) {
        openWritable();
        ContentValues values=null;
        try {

            values = new ContentValues();
            values.put("iid", logNote.getiID());
            values.put("sid", logNote.getsID());
            values.put("date", logNote.getDate());
            values.put("time", logNote.getTime());
            values.put("province", logNote.getProvince());
            values.put("nearestCity", logNote.getNearestCity());
            values.put("village", logNote.getVillage());
            values.put("exactLocation", logNote.getExactLocation());
            values.put("lon", logNote.getLon());
            values.put("lat", logNote.getLat());
            values.put("elevation", logNote.getElevation());
            values.put("habitat", logNote.getHabitat());
            values.put("name", logNote.getName());
            values.put("looksLike", logNote.getLooksLike());
            values.put("size", logNote.getSize());
            values.put("colour", logNote.getColour());
            values.put("behaviour", logNote.getBehaviour());
            values.put("otherNote", logNote.getOtherNote());

            long result = sqLiteDatabase.insert("logNote", null, values);
            if (result > 0) {
                return true;
            }
            return false;
        }finally {
            if (values!=null){

            }
        }

    }
    //delete lognote from lognote table
    public static boolean deleteLogNoteDetail(String lNID){
        openWritable();
        int result = sqLiteDatabase.delete("logNote", "inid=" + lNID, null);
        if (result>0){
            return true;
        }
        return false;
    }
    //update lognote
    public static boolean upDateLogNote(LogNote logNote){
        openWritable();
        ContentValues values=null;
        try {

            values = new ContentValues();
            //values.put("iid", logNote.getiID());
            //values.put("sid", logNote.getsID());
           // values.put("date", logNote.getDate());
            //values.put("time", logNote.getTime());
            values.put("province", logNote.getProvince());
            values.put("nearestCity", logNote.getNearestCity());
            values.put("village", logNote.getVillage());
            values.put("exactLocation", logNote.getExactLocation());
            values.put("lon", logNote.getLon());
            values.put("lat", logNote.getLat());
            values.put("elevation", logNote.getElevation());
            values.put("habitat", logNote.getHabitat());
            values.put("name", logNote.getName());
            values.put("looksLike", logNote.getLooksLike());
            values.put("size", logNote.getSize());
            values.put("colour", logNote.getColour());
            values.put("behaviour", logNote.getBehaviour());
            values.put("otherNote", logNote.getOtherNote());

            long result = sqLiteDatabase.update("logNote", values,"iid=?" ,new String[]{String.valueOf(logNote.getiID())});
            if (result > 0) {
                return true;
            }
            return false;
        }finally {
            if (values!=null){

            }
        }

    }
    //search lognote by iid
    public LogNote searchByIID(LogNote logNote){
        openWritable();
        try {
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM lognote WHERE iid='"+logNote.getiID()+"'",null);
           LogNote res = new LogNote();
            cursor.moveToFirst();
            if (cursor.getCount()>0){
                res.setiID(cursor.getInt(cursor.getColumnIndex("iid")));
                res.setsID(cursor.getInt(cursor.getColumnIndex("sid")));
                res.setDate(cursor.getString(cursor.getColumnIndex("date")));
                res.setTime(cursor.getString(cursor.getColumnIndex("time")));
                res.setProvince(cursor.getString(cursor.getColumnIndex("province")));
                res.setVillage(cursor.getString(cursor.getColumnIndex("village")));
                res.setExactLocation(cursor.getString(cursor.getColumnIndex("exactLocation")));
                res.setNearestCity(cursor.getString(cursor.getColumnIndex("nearestCity")));
                res.setLon(cursor.getDouble(cursor.getColumnIndex("lon")));
                res.setLat(cursor.getDouble(cursor.getColumnIndex("lat")));
                res.setElevation(cursor.getString(cursor.getColumnIndex("elevation")));
                res.setHabitat(cursor.getString(cursor.getColumnIndex("habitat")));
                res.setName(cursor.getString(cursor.getColumnIndex("name")));
                res.setSize(cursor.getString(cursor.getColumnIndex("size")));
                res.setLooksLike(cursor.getString(cursor.getColumnIndex("looksLike")));
                res.setColour(cursor.getString(cursor.getColumnIndex("colour")));
                res.setBehaviour(cursor.getString(cursor.getColumnIndex("behaviour")));
                res.setOtherNote(cursor.getString(cursor.getColumnIndex("otherNote")));

            }
            return (LogNote) res;
        }finally {

        }
    }



//get all lognote by name
   public ArrayList<LogNote> getAllByName(LogNote logNote){
       openWritable();
       try {
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM lognote WHERE lnid='"+logNote.getName()+"'",null);
            LogNote res = null;
            ArrayList<LogNote> logNotes=new ArrayList<>();
            while (cursor.moveToNext()){
                res.setiID(cursor.getInt(1));
                res.setsID(cursor.getInt(2));
                res.setDate(cursor.getString(3));
                res.setDate(cursor.getString(4));
                res.setProvince(cursor.getString(5));
                res.setVillage(cursor.getString(6));
                res.setExactLocation(cursor.getString(7));
                res.setNearestCity(cursor.getString(8));
                res.setLon(cursor.getDouble(9));
                res.setLat(cursor.getDouble(10));
                res.setElevation(cursor.getString(11));
                res.setHabitat(cursor.getString(12));
                res.setName(cursor.getString(13));
                res.setSize(cursor.getString(14));
                res.setLooksLike(cursor.getString(15));
                res.setColour(cursor.getString(16));
                res.setBehaviour(cursor.getString(17));
                res.setOtherNote(cursor.getString(18));

                logNotes.add(res);

            }
            return logNotes;
        }finally {

        }
    }



//get all position by name
    public static ArrayList<LatLng> getAllPositionsByshape(int sid){
    openWritable();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT lon,lat FROM lognote where sid='"+sid+"'",null);
        cursor.moveToFirst();
        ArrayList<LatLng> positions=new ArrayList<>();
        LatLng latLng=null;
        if (cursor!=null) {
        while (!cursor.isAfterLast()) {
            latLng = new LatLng(cursor.getDouble(cursor.getColumnIndex("lat")), cursor.getDouble(cursor.getColumnIndex("lon")));
            positions.add(latLng);
            System.out.println(latLng);
            cursor.moveToNext();
         }
        return positions;
    }
        return null;
    }


    //get all iid by province
    public static ArrayList<String> getAllIIDdByProvince(String province){
        openWritable();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT iid FROM lognote where province='"+province+"'",null);

        ArrayList<String> iidArrayList=new ArrayList<>();
        LatLng latLng=null;
        if (cursor!=null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String imageName=cursor.getInt(cursor.getColumnIndex("iid"))+".jpeg";

                iidArrayList.add(imageName);
                System.out.println(iidArrayList);
                cursor.moveToNext();
            }
            return iidArrayList;
        }
        return null;
    }

    //get all iid by name
    public static ArrayList<String> getAll_IIDdByname(String name){
        openWritable();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT iid FROM lognote where name='"+name+"'",null);
        cursor.moveToFirst();
        ArrayList<String> iidArrayList=new ArrayList<>();
        if (cursor!=null) {
            while (!cursor.isAfterLast()) {
                String imageName=cursor.getInt(cursor.getColumnIndex("iid"))+".jpeg";

                iidArrayList.add(imageName);
                System.out.println(iidArrayList);
                cursor.moveToNext();
            }
            return iidArrayList;
        }
        return null;
    }

    //get all iid by habitat
    public static ArrayList<String> getAll_IIDdByHabitat(String habitat){
        openWritable();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT iid FROM lognote where habitat='"+habitat+"'",null);
        cursor.moveToFirst();
        ArrayList<String> iidArrayList=new ArrayList<>();
        if (cursor!=null) {
            while (!cursor.isAfterLast()) {
                String imageName=cursor.getInt(cursor.getColumnIndex("iid"))+".jpeg";

                iidArrayList.add(imageName);
                System.out.println(iidArrayList);
                cursor.moveToNext();
            }
            return iidArrayList;
        }
        return null;
    }

    //get all iid by by size
    public static ArrayList<String> getAll_IIDdBySize(String size){
        openWritable();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT iid FROM lognote where size='"+size+"'",null);
        cursor.moveToFirst();
        ArrayList<String> iidArrayList=new ArrayList<>();
        if (cursor!=null) {
            while (!cursor.isAfterLast()) {
                String imageName=cursor.getInt(cursor.getColumnIndex("iid"))+".jpeg";

                iidArrayList.add(imageName);
                System.out.println(iidArrayList);
                cursor.moveToNext();
            }
            return iidArrayList;
        }
        return null;
    }
    //get all iid by shape
    public static ArrayList<String> getAll_IIDdByShape(String shape){
        openWritable();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT iid FROM lognote where sid='"+shape+"'",null);
        cursor.moveToFirst();
        ArrayList<String> iidArrayList=new ArrayList<>();
        if (cursor!=null) {
            while (!cursor.isAfterLast()) {
                String imageName=cursor.getInt(cursor.getColumnIndex("iid"))+".jpeg";

                iidArrayList.add(imageName);
                System.out.println(iidArrayList);
                cursor.moveToNext();
            }
            return iidArrayList;
        }
        return null;
    }

    //get all iid by looks like
    public static ArrayList<String> getAll_IIDdByLooksLike(String looksLike){
        openWritable();

        Cursor cursor=sqLiteDatabase.rawQuery("SELECT iid FROM lognote where sid='"+looksLike+"'",null);
        cursor.moveToFirst();
        ArrayList<String> iidArrayList=new ArrayList<>();
        if (cursor!=null) {
            while (!cursor.isAfterLast()) {
                String imageName=cursor.getInt(cursor.getColumnIndex("iid"))+".jpeg";

                iidArrayList.add(imageName);
                System.out.println(iidArrayList);
                cursor.moveToNext();
            }
            return iidArrayList;
        }
        return null;
    }

    //get all birds names
    public static ArrayList<String> getAllBirdName(){

        openWritable();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT distinct(name) FROM lognote ",null);

        ArrayList<String> birdNameArrayList=new ArrayList<>();
        if (cursor!=null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String birdName=cursor.getString(cursor.getColumnIndex("name"));

                birdNameArrayList.add(birdName);
                cursor.moveToNext();
            }
            return birdNameArrayList;
        }
        return null;
    }

    //get all log notes
    public ArrayList<LogNote> getAllLogNotes(){
        openWritable();
        try {
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM lognote",null);
            LogNote res = null;
            ArrayList<LogNote> logNotes=new ArrayList<>();
            while (cursor.moveToNext()){
                res.setiID(cursor.getInt(1));
                res.setsID(cursor.getInt(2));
                res.setDate(cursor.getString(3));
                res.setDate(cursor.getString(4));
                res.setProvince(cursor.getString(5));
                res.setVillage(cursor.getString(6));
                res.setExactLocation(cursor.getString(7));
                res.setNearestCity(cursor.getString(8));
                res.setLon(cursor.getDouble(9));
                res.setLat(cursor.getDouble(10));
                res.setElevation(cursor.getString(11));
                res.setHabitat(cursor.getString(12));
                res.setName(cursor.getString(13));
                res.setSize(cursor.getString(14));
                res.setLooksLike(cursor.getString(15));
                res.setColour(cursor.getString(16));
                res.setBehaviour(cursor.getString(17));
                res.setOtherNote(cursor.getString(18));

                logNotes.add(res);

            }
            return logNotes;
        }finally {

        }
    }

}
