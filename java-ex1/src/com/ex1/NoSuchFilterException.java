package com.ex1;

/**
 * NoSuchFilterException extend {@link Exception} class that throw
 * exception if the filter class dosen't exist
 */

class NoSuchFilterException extends Exception {

    /**
     * c-tor for the Exception
     */
    NoSuchFilterException() {
        super("available filters:\n-english\n-javascript\n");
    }

}
