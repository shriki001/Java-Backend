package com.ex2;

/**
 * class that extends {@link StatusResult}
 * handle url of image time status
 * */
class GoodResult extends StatusResult {
    /**
     * c-tor for the class create abstract object with the time + "ms"
     * @param url of the image
     * @param time in ms that took the process to finish
     * */
    GoodResult(String url, long time) {
        super(url, time + "ms");
    }
}
