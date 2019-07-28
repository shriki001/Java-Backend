package com.ex2;

import java.util.HashMap;
import java.util.Map;

/**
 * class TimeHandler that manage the time foe the task | fail | timout
 * */
class TimesHandler {
    // map that contain the time
    private Map<Integer, StatusResult> times;
    // instance of this class
    private static TimesHandler ourInstance = new TimesHandler();

    /**
     * @return instance to this class
     */
    static TimesHandler getInstance() {
        return ourInstance;
    }

    /**
     * c-tor for the class that create the map
     */
    private TimesHandler() {
        this.times = new HashMap<>();
    }

    /**
     * synchronized function that insert into the map the index pf the task
     * and the url after getting the time for the process
     * @param index of the thread
     * @param result is the url + the status of the url
    **/
    synchronized void add(Integer index, StatusResult result) {
        times.put(index, result);
    }

    /**
     * function that print the map
     * @return the content of the map to be printed
     * */
    String printMap() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer key: times.keySet()){
            String tmp = times.get(key).toString();
            stringBuilder.append(tmp).append("\n");
        }
        return stringBuilder.toString();
    }
}