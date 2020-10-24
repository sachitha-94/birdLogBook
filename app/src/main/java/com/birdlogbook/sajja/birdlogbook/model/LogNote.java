package com.birdlogbook.sajja.birdlogbook.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by sajja on 9/7/2016.
 */
public class LogNote {
    private int lNID;
    private int iID;
    private int sID;
    private String date;
    private String time;
    private String province;
    private String village;
    private String exactLocation;
    private String nearestCity;
    private double lon;
    private double lat;
    private String elevation;
    private String habitat;
    private String name;
    private String size;
    private String looksLike;
    private String colour;
    private String behaviour;
    private String otherNote;

    public LogNote() {
    }

    public LogNote(int lNID, int iID, int sID, String date, String time, String province, String village, String exactLocation, String nearestCity, double lon, double lat, String elevation, String habitat, String name, String size, String looksLike, String colour, String behaviour, String otherNote) {

        this.lNID = lNID;
        this.iID = iID;
        this.sID = sID;
        this.date = date;
        this.time = time;
        this.province = province;
        this.village = village;
        this.exactLocation = exactLocation;
        this.nearestCity = nearestCity;
        this.lon = lon;
        this.lat = lat;
        this.elevation = elevation;
        this.habitat = habitat;
        this.name = name;
        this.size = size;
        this.looksLike = looksLike;
        this.colour = colour;
        this.behaviour = behaviour;
        this.otherNote = otherNote;
    }

    public int getlNID() {
        return lNID;
    }

    public void setlNID(int lNID) {
        this.lNID = lNID;
    }

    public int getiID() {
        return iID;
    }

    public void setiID(int iID) {
        this.iID = iID;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getExactLocation() {
        return exactLocation;
    }

    public void setExactLocation(String exactLocation) {
        this.exactLocation = exactLocation;
    }

    public String getNearestCity() {
        return nearestCity;
    }

    public void setNearestCity(String nearestCity) {
        this.nearestCity = nearestCity;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }


    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLooksLike() {
        return looksLike;
    }

    public void setLooksLike(String looksLike) {
        this.looksLike = looksLike;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getOtherNote() {
        return otherNote;
    }

    public void setOtherNote(String otherNote) {
        this.otherNote = otherNote;
    }

    @Override
    public String toString() {
        return "LogNote{" +
                "lNID=" + lNID +
                ", iID=" + iID +
                ", sID=" + sID +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", province='" + province + '\'' +
                ", village='" + village + '\'' +
                ", exactLocation='" + exactLocation + '\'' +
                ", nearestCity='" + nearestCity + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", elevation='" + elevation + '\'' +
                ", habitat='" + habitat + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", looksLike='" + looksLike + '\'' +
                ", colour='" + colour + '\'' +
                ", behaviour='" + behaviour + '\'' +
                ", otherNote='" + otherNote + '\'' +
                '}';
    }
}
