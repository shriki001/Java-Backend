package com.ex2;

import java.sql.Timestamp;
/**
 * class to serialize and deserialize the Image Object
 * */
public class Image {
    private int id;
    private Timestamp date_added;
    private String url;

    /**
     * c-tor for the class
     * @param id is the id of the image
     * @param date_added is the time that the image insert into the database
     * @param url is the url of the image from the file
     * */
    Image(int id, Timestamp date_added, String url)
    {
        this.id = id;
        this.date_added = date_added;
        this.url = url;
    }

    /**
     * override function to print the class correctly
     * */
    public String toString()
    {
        return String.format("id: %d\ndate_added: %s\nurl: %s", this.id,
                this.date_added.toString(), this.url);
    }
}