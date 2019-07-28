package com.ex1;

public class RestrictedView {

    /**
     * The main function of the program
     *
     * @param args the RestrictedView arguments
     **/

    public static void main(String[] args) {
        SiteFilter siteFilter = new SiteFilter();
        try {
            siteFilter.filter(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}