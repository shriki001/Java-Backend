package com.ex1;

import java.util.HashMap;
import java.util.Map;

/**
 * @link WebPageFilterFactory an Factory pattern that create new filter class
 * from given flag
 */

class WebPageFilterFactory {
    // private map for the filters objects
    private Map<String, WebPageFilter> filters;

    /**
     * c-tor for the factory that create all the filter that exist into a map
     */

    WebPageFilterFactory() {
        this.filters = new HashMap<>();
        filters.put("-javascript", new RejectJavaScriptFilter());
        filters.put("-english", new EnglishDocumentsFilter());
    }

    /**
     * @param flagName is the filter that we want to create
     * @return the filter object
     * @throws NoSuchFilterException if there are no such filter
     */

    WebPageFilter createFilter(String flagName) throws NoSuchFilterException {
        WebPageFilter filter;

        filter = filters.get(flagName);
        if (filter == null) {
            throw new NoSuchFilterException();
        }
        return filter;
    }
}