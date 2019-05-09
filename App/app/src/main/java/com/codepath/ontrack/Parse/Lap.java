package com.codepath.ontrack.Parse;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Lap")
public class Lap extends ParseObject{
    public static final String KEY_OBJECTID = "objectId";
    public static final String KEY_Backlog = "BackLog";
    public static final String KEY_Name = "Name";
    public static final String KEY_Description = "Description";
    public static final String KEY_Priority = "Priority";
    public static final String KEY_BatonCount = "BatonCount";
    public static final String KEY_BatonCompleted = "BatonCompleted";
    public static final String KEY_TotalPoints = "TotalPoints";
    public static final String KEY_FileCount = "FileCount";
    public static final String KEY_DateSet = "DateSet";
    public static final String KEY_Completed = "Completed";

    //objectID
    public String getobjectID(){
        return getString(KEY_OBJECTID);
    }

    public void setobjectID(String objectID){
        put(KEY_OBJECTID, objectID);
    }

    //BackLog
    public ParseObject getBacklog(){return getParseObject(KEY_Backlog);}

    public void setBacklog(ParseObject BackLog){put(KEY_Backlog, BackLog);}

    //Name
    public String getName(){
        return getString(KEY_Name);
    }

    public void setName(String Name){
        put(KEY_Name, Name);
    }

    //Desctiption
    public String getDescription(){
        return getString(KEY_Description);
    }

    public void setDesciption(String Description){
        put(KEY_Description, Description);
    }

    //Priority
    public String getPriority(){
        return getString(KEY_Priority);
    }

    public void setPriority(String Priority){
        put(KEY_Priority, Priority);
    }

    //BatonCount
    public int getBatonCount(){
        return getInt(KEY_BatonCount);
    }

    public void setBatonCount(int BatonCount){
        put(KEY_BatonCount, BatonCount);
    }

    //BatonCompleted
    public int getBatonCompleted(){
        return getInt(KEY_BatonCompleted);
    }

    public void setBatonCompleted(int BatonCompleted){
        put(KEY_BatonCompleted, BatonCompleted);
    }

    //TotalPoints
    public int getTotalPoints(){
        return getInt(KEY_TotalPoints);
    }

    public void setTotalPoints(int TotalPoints){
        put(KEY_TotalPoints, TotalPoints);
    }

    //FileCount
    public int getFileCount(){
        return getInt(KEY_FileCount);
    }

    public void setFileCount(int FileCount){
        put(KEY_FileCount, FileCount);
    }

    //DateSet
    public Date getDateSet(){
        return getDate(KEY_DateSet);
    }

    public void setDateSet(Date DateSet){
        put(KEY_DateSet, DateSet);
    }

    //Completed
    public Boolean getCompleted(){
        return getBoolean(KEY_Completed);
    }

    public void setCompleted(Boolean Completed){
        put(KEY_Completed, Completed);
    }
}
