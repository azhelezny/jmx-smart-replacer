package main;

import replacers.Changers;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        //String filename = "/Users/Pavel/ideaProjects/test-jmeter-NEW/src/test/jmeter/plain-indexes.jmx";
        String filename = "/Users/azhelezny/projects/splice_machine/test-jmeter/src/test/jmeter/_transactions.jmx";

        try {
            //Changers.removeAllComments(filename);
            Changers.removeAllQueryNumbersFromTestName(filename);
            Changers.addQuerryNumberToTestName(filename);
            Changers.addCommentWithQueryNumber(filename);
            Changers.shrinkSamplerNames(filename,filename);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
