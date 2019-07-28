package com.ex2;


import java.io.IOException;
import java.sql.SQLException;

/**
 * the main process
 * @author Michael Shriki
 * @author Kfir Matityahu
 * */
public class Crawl {

    /**
     * The main function of the program
     * @param args the Crawl arguments
     **/
    public static void main(String[] args) {
        CrawlHandler crawlHandler;
        try {
            crawlHandler = new CrawlHandler(args);
        } catch (PositiveNonZeroNumberException | IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        try {
            crawlHandler.run();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}