package main;

import replacers.Changers;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        //String filename = "/Users/Pavel/ideaProjects/test-jmeter-NEW/src/test/jmeter/plain-indexes.jmx";
        String filename = "/Users/Pavel/ideaProjects/test/jmeter/src/test/jmeter/import_p.jmx";

        try {
            Changers.removeAllComments(filename);
            Changers.removeAllQueryNumbersFromTestName(filename);
            Changers.addQuerryNumberToTestName(filename);
            Changers.addCommentWithQueryNumber(filename);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
