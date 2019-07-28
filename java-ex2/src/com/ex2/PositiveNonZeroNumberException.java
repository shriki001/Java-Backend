package com.ex2;

/**
 * class PositiveNonZeroNumberException extends {@link Exception}
 * handle the case that the program args are not positive non zero
 */
class PositiveNonZeroNumberException extends Exception {

    /**
     * c-tor for the Exception
     * @param msg to display to user
     */
    PositiveNonZeroNumberException(String msg)
    {
        super(msg);
    }
}
