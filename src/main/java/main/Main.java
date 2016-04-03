package main;

import replacers.Changers;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        String filename = "/Users/azhelezny/projects/splice_machine/test/jmeter/src/test/jmeter/poc_workday.pishdrish";

        try {
            Changers.removeAllComments(filename);
            Changers.removeAllQueryNumbersFromTestName(filename);
            Changers.addQuerryNumberToTestName(filename, true);
            Changers.addCommentWithQueryNumber(filename);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
