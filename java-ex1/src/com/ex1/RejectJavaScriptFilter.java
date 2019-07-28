package com.ex1;

/**
 * RejectJavaScriptFilter that implements {@link WebPageFilter} interface
 * checking if url content is content script tag or not
 */

public class RejectJavaScriptFilter implements WebPageFilter {
    @Override
    public boolean accept(String url, String content) {
        String startScript = "<script";
        String endScript = "</script>";
        return !(content.contains(startScript) &&
                content.contains(endScript));
    }
}