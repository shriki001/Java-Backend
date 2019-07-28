package com.ex1;

import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Map;

/**
 * EnglishDocumentsFilter that implements {@link WebPageFilter} interface
 * checking if url content is a English web page or not
 */

public class EnglishDocumentsFilter implements WebPageFilter {
    // private Map object to keep the FrequencyTable of all the English Alphabet
    private Map<Character, Double> globalFrequencyTable = new HashMap<>();

    // private Map object that will keep the content FrequencyTable
    private Map<Character, Double> frequencyTable = new HashMap<>();

    /**
     * the c-tor for the class that initialize the global Frequency Table
     */
    EnglishDocumentsFilter() {
        initFrequencyTable();
    }

    /**
     * private function that do the initialize
     */
    private void initFrequencyTable() {
        globalFrequencyTable.put('a', 0.0748);
        globalFrequencyTable.put('b', 0.0134);
        globalFrequencyTable.put('c', 0.0411);
        globalFrequencyTable.put('d', 0.0308);
        globalFrequencyTable.put('e', 0.1282);
        globalFrequencyTable.put('f', 0.0240);
        globalFrequencyTable.put('g', 0.0185);
        globalFrequencyTable.put('h', 0.0414);
        globalFrequencyTable.put('i', 0.0725);
        globalFrequencyTable.put('j', 0.0014);
        globalFrequencyTable.put('k', 0.0053);
        globalFrequencyTable.put('l', 0.0403);
        globalFrequencyTable.put('n', 0.0673);
        globalFrequencyTable.put('m', 0.0340);
        globalFrequencyTable.put('o', 0.0785);
        globalFrequencyTable.put('p', 0.0314);
        globalFrequencyTable.put('q', 0.0010);
        globalFrequencyTable.put('r', 0.0609);
        globalFrequencyTable.put('s', 0.0614);
        globalFrequencyTable.put('t', 0.1002);
        globalFrequencyTable.put('u', 0.0316);
        globalFrequencyTable.put('v', 0.0108);
        globalFrequencyTable.put('w', 0.0131);
        globalFrequencyTable.put('x', 0.0044);
        globalFrequencyTable.put('y', 0.0127);
        globalFrequencyTable.put('z', 0.0011);
    }

    @Override
    public boolean accept(String url, String content) {
        String workingContent = Jsoup.parse(content).text();
        int contentSize = workingContent.length();
        for (Character i : workingContent.toCharArray()) {
            frequencyTable.merge(Character.toLowerCase(i),
                    1D / contentSize, (a, b) -> a + (b));
        }
        double variance = 0;
        for (Character key : globalFrequencyTable.keySet())
            if (frequencyTable.get(key) != null)
                variance += Math.pow((globalFrequencyTable.get(key)
                        - frequencyTable.get(key)), 2);
        frequencyTable.clear();
        return variance < 0.04;
    }
}