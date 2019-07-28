package com.ex2;

import java.sql.*;
import java.util.ArrayList;

/**
 * class that connect to the database and read/write data from/to the database
 * Singleton design pattern
 */
class Database {
    private static Database ourInstance; //private instance member
    // insert url to database query
    private static final String INSERT = "insert into images(url) values (?);";
    // select url from database query
    private static final String SELECT_URL = "select url from images where " +
            "url = (?);";
    // select all image from database query
    private static final String SELECT_ALL = "select * from images;";
    // sql driver string
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    // phpmyadmin url for the database connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ex2" +
            "?user=root&password=";

    static {
        try {
            ourInstance = new Database(); // initialize the database once
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // connection to database object
    private Connection connection;

    /**
     * @return instance to this class
     */
    static Database getInstance() {
        return ourInstance;
    }

    /**
     * c-tor for the class that create connection to the database
     * @throws SQLException if there any problem with the database object
     */
    private Database() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            System.err.println("Failed to load the driver");
        }
        // connect to ex2 database
        connection = DriverManager.getConnection(DB_URL);
    }

    /**
     * function that insert new image to the database
     * @param url to put inside the table
     *        the id and data_added have default values
     * @throws SQLException if there any problem with the database object
     */
    void insertNewImage(String url) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, url);
        preparedStatement.executeUpdate();
    }

    /**
     * function that check if image exist in the database
     * @param url to check inside the table
     * @return true if exist and false if doesnt
     * @throws SQLException if there any problem with the database object
     */
    boolean checkIfExist(String url) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_URL);
        preparedStatement.setString(1, url);
        ResultSet result = preparedStatement.executeQuery();
        return result.next();
    }

    /**
     * function that get all data from the database and create an ArrayList
     * of {@link Image} for all the data
     * @return ArrayList of Images
     * @throws SQLException if there any problem with the database object
     */
    ArrayList<Image> getAllData() throws SQLException {
        ArrayList<Image> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            int id = result.getInt("id");
            Timestamp date = result.getTimestamp("date_added");
            String url = result.getString("url");
            list.add(new Image(id, date, url));
        }
        return list;
    }
}