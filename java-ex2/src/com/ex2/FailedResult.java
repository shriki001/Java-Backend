package com.ex2;

/**
 * class that extends {@link StatusResult}
 * handle the failed status
 * */
class FailedResult extends StatusResult {
    private static final String status = "failed";
    /**
     * c-tor for the class create abstract object with the status "failed"
     * @param url that is not an image
     * */
    FailedResult(String url) {
        super(url, status);
    }
}
