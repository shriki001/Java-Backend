package com.ex1;

/**
 * PageNotFoundException extend {@link Exception} class that throw
 * exception if the page not found
 */

class PageNotFoundException extends Exception {
    /**
     * c-tor for the Exception
     */
    PageNotFoundException() {
        super("page not found");
    }
}
