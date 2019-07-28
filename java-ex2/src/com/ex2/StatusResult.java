package com.ex2;

/**
 * class that implements {@link URLResults}
 * */
abstract class StatusResult implements URLResults{
    protected String url; // potential url of an image
    protected String status; // status in ms | failed | timeout

    /**
     * c-tor for the class
     * @param url of the image
     * @param status is the time in ms | failed | timeout
     * */
    StatusResult(String url, String status) {
        this.url = url;
        this.status = status;
    }

    /**
    * toString override to print the url + status
    **/
    @Override
    public String toString() {
        return url + ": " + status;
    }
}

