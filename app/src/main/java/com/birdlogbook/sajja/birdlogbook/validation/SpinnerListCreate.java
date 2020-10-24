package com.birdlogbook.sajja.birdlogbook.validation;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajja on 9/8/2016.
 */
public class SpinnerListCreate {
    public static List<String> setProvinceSpinner(){
        List<String> provinceList=new ArrayList<String>();
        provinceList.add("Central");
        provinceList.add("Eastern");
        provinceList.add("North Central");
        provinceList.add("Northern");
        provinceList.add("North Western");
        provinceList.add("Sabaragamuwa");
        provinceList.add("Southern");
        provinceList.add("Uva");
        provinceList.add("Western");
        return provinceList;

    }
    public static List<String> setCitySpinner(String province){


        List<String> cityList=new ArrayList<>();
        switch (province){
            case "Central":
                cityList.add("Kandy");
                cityList.add("Nuwara Eliya");
                cityList.add("Matale");
                break;
            case "Southern":
                cityList.add("Galle");
                cityList.add("Matara");
                cityList.add("Hambantota");
                break;
            case "Western":
                cityList.add("Gampaha");
                cityList.add("Colombo");
                cityList.add("Kalutara");
                break;
            case "North Central":
                cityList.add("Polonnaruwa");
                cityList.add("Anuradhapura");
                break;
            case "Eastern":
                cityList.add("Ampara");
                cityList.add("Batticaloa");
                cityList.add("Trincomalee");
                break;
            case "Uva":
                cityList.add("Badulla");
                cityList.add("Monaragala");
                break;
            case "Sabaragamuwa":
                cityList.add("Kegalle");
                cityList.add("Ratnapura");
                break;
            case "Northern":
                cityList.add("Mullaitivu");
                cityList.add("Vavuniya");
                cityList.add("Mannar");
                cityList.add("Kilinochchi");
                cityList.add("Jaffna");
                break;
            case "North Western":
                cityList.add("Kurunegala");
                cityList.add("Puttalam");
                break;
        }
        return cityList;
    }
    public static List<String> setHabitatSpinner(){
        List<String> habitatList=new ArrayList<>();
        habitatList.add("Beach");
        habitatList.add("Dry zone forest");
        habitatList.add("Forest");
        habitatList.add("Home guarden");
        habitatList.add("Lake");
        habitatList.add("Low land rainforest");
        habitatList.add("Mountain");
        habitatList.add("Mountain rainforest");
        habitatList.add("River");
        habitatList.add("Riverine forest");
        habitatList.add("Scrubland");
        habitatList.add("Any other");

        return habitatList;
    }
    public static List<String> setElevationSpinner(){
        List<String> elevationList=new ArrayList<>();
        elevationList.add("0-100m");
        elevationList.add("100-200m");
        elevationList.add("200-300m");
        elevationList.add("300-400m");
        elevationList.add("400-500m");
        elevationList.add("500-600m");
        elevationList.add("600-700m");
        elevationList.add("700-800m");
        elevationList.add("800-900m");
        elevationList.add("900-1000m");
        elevationList.add("1000-1100m");
        elevationList.add("1100-1200m");
        elevationList.add("120-1300m");
        elevationList.add("130-1400m");
        elevationList.add("1400-1500m");
        elevationList.add("1500-1600m");
        elevationList.add("1600-1700m");
        elevationList.add("1700-1800m");
        elevationList.add("1800-1900m");
        elevationList.add("1900-200m");
        elevationList.add("2000-2100m");
        elevationList.add("2100-2200m");
        elevationList.add("2200-2300m");
        elevationList.add("2300-2400m");
        elevationList.add("2400-2500m");
        elevationList.add(">2500m");
        return elevationList;
    }

    public static List<String> setLookLikeSpinner(){
        List<String> lookLikeList=new ArrayList<>();
        lookLikeList.add("> Eagle");
        lookLikeList.add("~ Kite");
        lookLikeList.add("> Crow");
        lookLikeList.add("< Crow");
        lookLikeList.add("~ Myna");
        lookLikeList.add("~ Babbler");
        lookLikeList.add("< Sunbird");

        return lookLikeList;
    }
    public static List<String> setShapeSpinner(){
        List<String> shapeList=new ArrayList<>();
        shapeList.add("");
        shapeList.add("1");
        shapeList.add("2");
        shapeList.add("3");
        shapeList.add("4");
        shapeList.add("5");
        shapeList.add("6");
        shapeList.add("7");
        shapeList.add("8");
        shapeList.add("9");
        shapeList.add("10");

        return shapeList;
    }
    public static List<String> setShapeSpinner1(){
        List<String> shapeList=new ArrayList<>();
        shapeList.add("All-0");
        shapeList.add("crow-1");
        shapeList.add("cuckoos-2");
        shapeList.add("duck-3");
        shapeList.add("eagle-4");
        shapeList.add("owl-5");
        shapeList.add("sun bird-6");
        shapeList.add("swallows-7");
        shapeList.add("wood pecker-8");
        shapeList.add("pigeons-9");
        shapeList.add("fowl-10");

        return shapeList;
    }

    public static List<String> setSearchBy(){
        List<String> searchByList=new ArrayList<>();
        searchByList.add("SEARCH");
        searchByList.add("Bird Name");
       // searchByList.add("City");
       // searchByList.add("date");
       // searchByList.add("Elevation");
        searchByList.add("Habitat");
        searchByList.add("Province");
        searchByList.add("Looks Like");
        searchByList.add("Shape");

        return searchByList;
    }
    public static List<String> setUserType(){
        List<String> userList=new ArrayList<>();
        userList.add("Nature Lover");
        userList.add("Scientist");
        return userList;
    }

    public static List<String> setSizeSpinner(){
        List<String> userList=new ArrayList<>();
        userList.add("5-10");
        userList.add("11-15");
        userList.add("16-20");
        userList.add("21-30");
        userList.add("31-45");
        userList.add("46-60");
        userList.add("61-90");
        userList.add("90 <");

        return userList;
    }
}
