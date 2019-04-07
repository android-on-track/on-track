package com.codepath.ontrack.Parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseObject;

import com.parse.ParseUser;

@ParseClassName("BackLog")
public class BackLog extends ParseObject{
    public static final String KEY_USER = "user";
    public static final String KEY_NAME = "Name";
    public static final String KEY_Completed = "Completed";
    public static final String KEY_NumofTask = "NumofTask";
    public static final String KEY_NumofCompleted = "NumOfCompleted";
    public static final String KEY_OBJECTID = "objectId";


    //User
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser parseUser){
        put(KEY_USER, parseUser);
    }

    //Name
    public String getName(){
        return getString(KEY_NAME);
    }

    public void setName(String Name){
        put(KEY_NAME, Name);
    }

    //Completed
    public Boolean getCompleted(){
        return getBoolean(KEY_Completed);
    }

    public void setCompleted(Boolean Completed){
        put(KEY_Completed, Completed);
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

    //objectId
    public String getobjectId(){
        return getString(KEY_NumofCompleted);
    }


}
