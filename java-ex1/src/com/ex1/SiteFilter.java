package com.ex1;

import java.util.ArrayList;

/**
 * SiteFilter class is an interface that another Filter classes can
 * implement and override the accept function
 */
class SiteFilter {
    private String url;         // url input from user
    private String content;          // content of the site
    private WebPageFilterFactory webPageFilterFactory;   // Factory for creating filters
    private ArrayList<WebPageFilter> filters;  // array of the selected filters
    private CompositeWebPageFilter compositeFilter; // filter that composites the selected filters

    /**
     * @param args from the main function
     * @throws NoSuchFilterException if there are no such filter
     * @throws PageNotFoundException if the page from given url not found
     */

    void filter(String[] args) throws NoSuchFilterException, PageNotFoundException {
        webPageFilterFactory = new WebPageFilterFactory();
        filters = new ArrayList<>();
        HTMLParser parser = new HTMLParser();

        if (args.length == 0)
            throw new NoSuchFilterException();

        url = args[args.length - 1];
        content = parser.getHTMLFromUrl(url);

        for (int i = 0; i < args.length - 1; i++)
            filters.add(webPageFilterFactory.createFilter(args[i]));

        compositeFilter = new CompositeWebPageFilter(filters);

        if (compositeFilter.accept(url, content))
            System.out.println(content);
        else
            System.out.println("access denied");
    }
}