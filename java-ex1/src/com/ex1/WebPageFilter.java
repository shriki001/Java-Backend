package com.ex1;

/**
 *  WebPageFilter an interface that another Filter classes can
 *  implement and override the accept function
 * */

public interface WebPageFilter {
    /**
     * @param url string of the url
     * @param content content of html doc from url
     * @return true or false depended the flag
    * */
    boolean accept(String url, String content);
}