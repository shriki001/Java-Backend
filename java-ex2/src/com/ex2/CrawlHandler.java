package com.ex2;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * class that handle the crawl main program
 * */
class CrawlHandler {

    private OpenFile openFile; // file operations
    // the number of threads
    private int thread;
    // the timeout delay in ms
    private int timeout;
    // the number of tries
    private int tries;
    // tha map class that we need for the time of the url's
    private TimesHandler timesHandler;

    /**
     * c-tor of CrawlHandler
     * @param args the Crawl arguments
     * @throws PositiveNonZeroNumberException if the argument checker failed
     * @throws IOException if the file not open correctly
     **/
    CrawlHandler(String[] args) throws PositiveNonZeroNumberException,
            IOException {
        // check arguments validation
        ArgumentsChecker argumentsChecker = new ArgumentsChecker(args);
        this.thread = argumentsChecker.getNumOfThread();
        this.timeout = argumentsChecker.getTimeout();
        this.tries = argumentsChecker.getTries();
        // the path to the file to pass to OpenFile Class
        String path = argumentsChecker.getPath();
        this.openFile = new OpenFile(path);
        this.timesHandler = TimesHandler.getInstance();

    }

    /**
     * private method that print the times to execute the url connections
     * @throws SQLException if there any problem with the database object
     */
    void run() throws SQLException {
        // get the urls from OpenFile class as a ArrayList
        ArrayList<String> urls = openFile.getUrls();
        ExecutorService pool = Executors.newFixedThreadPool(thread);
        int index = 0;
        for (String url : urls) {
            // create new Task and run it
            Task task = new Task(url, timeout, tries, index++);
            pool.execute(task);
        }
        pool.shutdown();
        //we need the pool done to take all
        // of the information that we need to print
        while (!pool.isTerminated()) ;
        // get the images from the database1
        ArrayList<Image> imageList = Database.getInstance().getAllData();
        System.out.println(timesHandler.printMap());
        Scanner cin = new Scanner(System.in);
        System.out.println("print all images in database? (y)");
        String ans = cin.nextLine();
        if (ans.equals("y"))
            printTheImage(imageList);
    }

    /**
     * private method that print all the image that exist in the database
     * @param images the arrayList that contain the images
     */
    private void printTheImage(ArrayList<Image> images) {
        System.out.println("All The Images That Inside The Database:");
        for (Image image : images)
            System.out.println("------ Row " + images.indexOf(image) +
                    " ------\n" + image);
    }
}