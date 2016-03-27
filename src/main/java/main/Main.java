package main;

import replacers.Changers;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        String filename = "/Users/Pavel/ideaProjects/test/jmeter/src/test/jmeter/import.jmx";
        try {
            Changers.addCommentWithQueryNumber(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
