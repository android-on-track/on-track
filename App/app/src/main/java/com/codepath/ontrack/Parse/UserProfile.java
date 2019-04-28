package com.codepath.ontrack.Parse;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("UserProfile")
public class UserProfile extends ParseObject {

    public static final String KEY_OBJECTID = "objectID";
    public static final String KEY_USER = "user";
    public static final String KEY_FIRSTNAME = "FirstName";
    public static final String KEY_LASTNAME = "LastName";
    public static final String KEY_ABOUT = "About";
    public static final String KEY_TITLE = "Title";
    public static final String KEY_TASKCOMPLETED = "TaskCompleted";
    public static final String KEY_POINTS = "Points";
    public static final String KEY_PROFILEPIC = "ProfilePic";


    //User
    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser parseUser){
        put(KEY_USER, parseUser);
    }

    //objectID
    public String getobjectID(){
        return getString(KEY_OBJECTID);
    }

    public void setobjectID(String objectID){
        put(KEY_OBJECTID, objectID);
    }


    //Firstname
    public String getFirstName(){
        return getString(KEY_FIRSTNAME);
    }

    public void setFirstName(String FirstName){
        put(KEY_FIRSTNAME, FirstName);
    }

    //LastName
    public String getLastName(){
        return getString(KEY_LASTNAME);
    }

    public void setLastName(String LastName){
        put(KEY_LASTNAME, LastName);
    }

    //ProfilePic
    public ParseFile getProfilePic(){
        return getParseFile(KEY_PROFILEPIC);
    }

    public void setProfilePic(ParseFile parseFile){
        put(KEY_PROFILEPIC, parseFile);
    }

    //Points
    public int getPoints(){
        return getInt(KEY_POINTS);
    }

    public void setPoints(int Points){
        put(KEY_POINTS, Points);
    }

    //TaskCompleted
    public int getTaskCompleted(){
        return getInt(KEY_TASKCOMPLETED);
    }

    public void setTaskCompleted(int TaskCompleted){
        put(KEY_TASKCOMPLETED, TaskCompleted);
    }

    //About
    public String getAbout(){
        return getString(KEY_ABOUT);
    }

    public void setAbout(String About){
        put(KEY_ABOUT, About);
    }

    //Title
    public String getTitle(){
        return getString(KEY_TITLE);
    }

    public void setTitle(String Title){
        put(KEY_TITLE, Title);
    }
}
