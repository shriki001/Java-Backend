package com.ex2;

import java.io.*;
import java.util.ArrayList;

/**
 * class that open the file and read the urls
 */
class OpenFile {

    private ArrayList<String> urls; // ArrayList for the urls

    /**
     * method that open file from given path
     * @param path to the file
     * @throws FileNotFoundException if the file not found
     */
    OpenFile(String path) throws IOException {
        urls = new ArrayList<>();
        String cwd = System.getProperty("user.dir");
        File file = new File(cwd + "/src/" + path);

        if (!file.exists()) {
            throw new FileNotFoundException("File Doesnt Exist");
        }
        if (file.length() == 0) {
            throw new FileNotFoundException("File is Empty!");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null)
                urls.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method that return the urls as an ArrayList
     * @return ArrayList of the urls
     */
    ArrayList<String> getUrls() {
        return urls;
    }
}
