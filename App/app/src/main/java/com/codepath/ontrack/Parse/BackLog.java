package com.codepath.ontrack.Parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseObject;

import com.parse.ParseUser;

@ParseClassName("BackLog")
public class BackLog extends ParseObject{
    public static final String KEY_OBJECTID = "objectId"; //NEW
    public static final String KEY_USER = "user";
    public static final String KEY_NumofTask = "NumofTask";
    public static final String KEY_NumofCompleted = "NumofCompleted";

    //objectID
    public String getobjectID(){
        return getString(KEY_OBJECTID);
    }

    public void setobjectID(String objectID){
        put(KEY_OBJECTID, objectID);
    }

    //User
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser parseUser){
        put(KEY_USER, parseUser);
    }


    //NumofTask
    public int getNumofTask(){
        return getInt(KEY_NumofTask);
    }

    public void setNumofTask(int NumofTask){
        put(KEY_NumofTask, NumofTask);
    }


    //NumofCompleted
    public int getNumofCompleted(){
        return getInt(KEY_NumofCompleted);
    }

    public void setNumofCompleted(int NumofCompleted){
        put(KEY_NumofCompleted, NumofCompleted);
    }
}
