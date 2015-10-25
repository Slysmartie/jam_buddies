package com.example.mitrikyle.jambuds;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by mitrikyle on 10/24/2015.
 */

@ParseClassName("Jam")
public class Jam extends ParseObject{
    public Jam() {
    }

    public void setCreator(ParseUser user){
        put("creator", user);
    }

    public ParseUser getCreator(){
        return (ParseUser) get("creator");
    }

    public void setDescription(String description){
        put("description", description);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setLocation(String location){
        put("location", location);
    }

    public String getLocation(){
        return getString("location");
    }

    public String getDate(){
        return getString("date");
    }

    public void setDate(String date){
        put("date", date);
    }

    public String getTitle(){
        return getString("title");
    }

    public void setTitle(String title){
        put("title", title);
    }

    public String getGenre(){
        return getString("genre");
    }

    public void setGenre(String genre){
        put("genre", genre);
    }


}
