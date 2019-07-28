package com.ex1;

import java.util.ArrayList;

/**
 * CompositeWebPageFilter that implements {@link WebPageFilter} interface
 * and do the accept methods of all the filters that past over the program
 * arguments
 **/

public class CompositeWebPageFilter implements WebPageFilter {
    // private ArrayList object that keep the filters
    private ArrayList<WebPageFilter> filtersList;

    /**
     * c-tor for the CompositeWebPageFilter class
     * @param filtersList as ArrayList that contain tha filters
     *                    and assign it to his private ArrayList
     **/
    CompositeWebPageFilter(ArrayList<WebPageFilter> filtersList) {
        this.filtersList = filtersList;
    }

    @Override
    public boolean accept(String url, String content) {
        for (WebPageFilter filter : filtersList)
            if (!filter.accept(url, content))
                return false;
        return true;
    }
}