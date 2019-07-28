package com.ex1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * take an url and extract the html content from this url
 */

class HTMLParser {
    /**
     * @param url the url to parse html code from
     * @return string that contain the code
     * @throws PageNotFoundException if the url unavailable
     */
    String getHTMLFromUrl(String url) throws PageNotFoundException {
        String content;
        try {
            content = parseHTMLFromUrl(url);
        } catch (Exception e) {
            throw new PageNotFoundException();
        }

        return content;
    }

    /**
     * @param url the url to parse html code from
     * @return string that contain the code
     * @throws Exception if the url not open correctly
     */
    private String parseHTMLFromUrl(String url) throws Exception {
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
            content.append(inputLine).append("\n");
        in.close();

        return content.toString();
    }
}