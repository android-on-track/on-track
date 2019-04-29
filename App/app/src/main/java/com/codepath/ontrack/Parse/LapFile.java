package com.codepath.ontrack.Parse;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("LapFile")
public class LapFile extends ParseObject {

    public static final String KEY_OBJECTID = "objectId";
    public static final String KEY_Lap = "Lap";
    public static final String KEY_Image = "Image";


    //objectID
    public String getobjectID(){
        return getString(KEY_OBJECTID);
    }

    public void setobjectID(ParseObject objectID){
        put(KEY_OBJECTID, objectID);
    }

    //Lap
    public ParseObject getLap(){return getParseObject(KEY_Lap);}

    public void setLap(String Lap) {put(KEY_Lap, Lap);}


    //Imaage
    public ParseFile getImage(){
        return getParseFile(KEY_Image);
    }

    public void setImage(ParseFile parseFile){
        put(KEY_Image, parseFile);
    }
}
