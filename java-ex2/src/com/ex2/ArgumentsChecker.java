package com.ex2;

/**
 * class that check the {@link Crawl} arguments
 */
class ArgumentsChecker {
    private int num_of_thread, timeout, tries;
    private String path;

    /**
     * c-tor ro the class
     * @param args to check validation
     * @throws PositiveNonZeroNumberException if the number aren't positive
     * non zero
     */
    ArgumentsChecker(String[] args) throws PositiveNonZeroNumberException {
        if (args.length != 4) // if argument is missing
            throw new PositiveNonZeroNumberException("Usage: pool size, timeout, tries, file path");

        if (Integer.parseInt(args[0]) > 0) { // num of thread pool
            num_of_thread = Integer.parseInt(args[0]);
        } else {
            throw new PositiveNonZeroNumberException("Pool Size Mast Be " +
                    "Positive Non Zero");
        }
        if (Integer.parseInt(args[1]) > 0) { // timeout delay
            timeout = Integer.parseInt(args[1]);
        } else {
            throw new PositiveNonZeroNumberException("Timeout Delay " +
                    "Mast Be Positive Non Zero");
        }
        if (Integer.parseInt(args[2]) > 0) { // number of retries
            tries = Integer.parseInt(args[2]);
        } else {
            throw new PositiveNonZeroNumberException("Number Of Retries " +
                    "Mast Be At Least One");
        }
        path = args[3]; // the name of the file
    }

    /**
     * @return the number of threads
     */
    int getNumOfThread() {
        return num_of_thread;
    }

    /**
     * @return the timeout delay in ms
     */
    int getTimeout() {
        return timeout;
    }

    /**
     * @return the number of tries for reconnect
     */
    int getTries() {
        return tries;
    }

    /**
     * @return the path to the urls txt file
     */
    String getPath() {
        return path;
    }
}
