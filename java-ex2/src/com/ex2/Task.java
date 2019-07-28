package com.ex2;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

/**
 * class Task implements {@link Runnable} that execute Thread from the Thread
 * pool and open url connection to get image (if exist) and insert it to th
 * database
 */
public class Task implements Runnable {
    private int index; // index for each task
    private String url; //url of the image
    private long timeout; // timeout in ms to Thread got sleep
    private int tries; // number of tries to reconnect
    // map to put the time in ms of the process time or failure or timeout
    private TimesHandler timesHandler;
    private Database database; //instance of the database class

    /**
     * c-tor fot the Task
     * @param url     is the url of the image
     * @param timeout is the time for the thread sleep in ms
     * @param tries   is the number of tries for the connection
     * @param index   is the index of the thread run for the times timesHandler
     */
    Task(String url, long timeout, int tries, int index) {
        this.index = index;
        this.url = url;
        this.timeout = timeout;
        this.tries = tries;
        this.timesHandler = TimesHandler.getInstance();
        this.database = Database.getInstance();
    }

    /**
     * the function that perform the run of the task
     */
    @Override
    public void run() {
        long start_time = System.currentTimeMillis();
        for (int i = 0; i < tries; i++) {
            try {
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
                int code = conn.getResponseCode();
                if (!(code >= 200 && code < 300)) {
                    // code 200 -> 299 is http OK response
                    goSleep();
                    continue;
                }

                String type = conn.getContentType();
                if (type.startsWith("image/")) {
                    connectToDatabase();
                    timesHandler.add(index, new GoodResult(url,
                            System.currentTimeMillis() - start_time));
                    return;
                }

            } catch (MalformedURLException |SQLException e) {
                // maybe url is malformed or url is too long for varchar(128)
                // or else
                timesHandler.add(index, new FailedResult(url));
                return;
            } catch (IOException e) {// maybe some problem with the url
                    goSleep();
            }
        }
        // if we get here seems that we get timeout
        timesHandler.add(index, new TimeoutResult(url));
    }

    /**
     * function that make the thread sleep for the timeout
     */
    private void goSleep() {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * function that check if url is in the database
     * if not the function will insert it to the database
     * @throws SQLException from database
     */
    private synchronized void connectToDatabase() throws SQLException {
        if (!database.checkIfExist(url))
            database.insertNewImage(url);
    }
}