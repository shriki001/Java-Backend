package com.ex2;

/**
 * class that extends {@link StatusResult}
 * handle the timeout status
 * */
class TimeoutResult extends StatusResult {
    private static final String status = "timeout";
    /**
     * c-tor for the class create abstract object with the status "timeout"
     * @param url of the image
     * */
    TimeoutResult(String url) {
        super(url, status);
    }
}
