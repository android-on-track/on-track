package com.codepath.ontrack.Parse;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Baton")
public class Baton extends ParseObject {
    public static final String KEY_OBJECTID = "objectID"; //NEW
    public static final String KEY_LAP = "Lap";  //NEW
    public static final String KEY_Name = "Name";
    public static final String KEY_Description = "Description";
    public static final String KEY_Priority = "Priority"; //NEW
    public static final String KEY_Points = "Points";
    public static final String KEY_Completed = "Completed";


    //objectID
    public String getobjectID(){
        return getString(KEY_OBJECTID);
    }

    public void setobjectID(String objectID){
        put(KEY_OBJECTID, objectID);
    }

    //Lap
    public ParseObject getLap(){
        return getParseObject(KEY_LAP);
    }

    public void setLap(ParseObject Lap){
        put(KEY_LAP, Lap);
    }

    //Name
    public String getName(){
        return getString(KEY_Name);
    }

    public void setName(String Name){
        put(KEY_Name, Name);
    }


    //Description
    public String getDescription(){
        return getString(KEY_Description);
    }

    public void setDescription(String Description){
        put(KEY_Description, Description);
    }


    //Priority
    public String getPriority() { return getString(KEY_Priority); }

    public void setPriority(String Priority) { put(KEY_Priority, Priority);}


    //Points
    public int getPoints(){
        return getInt(KEY_Points);
    }

    public void setPoints(int Points){
        put(KEY_Points, Points);
    }


    //Completed
    public Boolean getCompleted(){
        return getBoolean(KEY_Completed);
    }

    public void setCompleted(Boolean Completed){
        put(KEY_Completed, Completed);
    }


}


