package com.codepath.ontrack.Parse;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Baton")
public class Baton extends ParseObject {
    public static final String KEY_USER = "user";
    public static final String KEY_BackLogID = "BackLogID";
    public static final String KEY_Points = "Points";
    public static final String KEY_Pic = "Pic";
    public static final String KEY_Completed = "Completed";
    public static final String KEY_Name = "Name";
    public static final String KEY_NumofDays = "NumofDays";
    public static final String KEY_Description = "Description";

    //User
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser parseUser){
        put(KEY_USER, parseUser);
    }

    //BackLogID
    public ParseObject getBackLogID(){
        return getParseObject(KEY_BackLogID);
    }

    public void setBackLogID(String BackLogID){
        put(KEY_BackLogID, BackLogID);
    }

    //Points
    public int getPoints(){
        return getInt(KEY_Points);
    }

    public void setPoints(int Points){
        put(KEY_Points, Points);
    }

    //Pic
    public ParseFile getPic(){
        return getParseFile(KEY_Pic);
    }

    public void setPic(ParseFile parseFile){
        put(KEY_Pic, parseFile);
    }

    //Completed
    public Boolean getCompleted(){
        return getBoolean(KEY_Completed);
    }

    public void setCompleted(Boolean Completed){
        put(KEY_Completed, Completed);
    }

    //Name
    public String getName(){
        return getString(KEY_Name);
    }

    public void setName(String Name){
        put(KEY_Name, Name);
    }


    //NumofDays
    public int getNumofDays(){
        return getInt(KEY_NumofDays);
    }

    public void setNumofDays(int NumofDays){
        put(KEY_NumofDays, NumofDays);
    }

    //Description
    public String getDescription(){
        return getString(KEY_Description);
    }

    public void setDescription(String Description){
        put(KEY_Description, Description);
    }



}


