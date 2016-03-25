package sandbox;

import utils.file.FileUtils;
import utils.text.PlainTextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andrey Zhelezny
 *         Date: 3/25/16
 */
public class AlternativeMain {
    public static void main(String[] args) throws IOException {
        String fileName = "/Users/azhelezny/Desktop/triggers/done/advanced_operations.sql.jmx";
        String outputFileName = "/Users/azhelezny/Desktop/triggers/done/advanced_operations.new.jmx";
        List<String> lexemes = new ArrayList<String>();
        lexemes.add(PlainTextUtils.createTableLexeme);
        lexemes.add(PlainTextUtils.createTriggerLexeme);
        List<String> fileContent = FileUtils.readFileToList(fileName);
        List<String> namesToReplace = PlainTextUtils.findAllOccurrencesWithRegexp(fileContent, lexemes);
        Map<String,String> replacementPatterns = new HashMap<String, String>();
        for (String name: namesToReplace)
            replacementPatterns.put(String.format(PlainTextUtils.nonQutedContentReplacementPattern, name), "\\${SCHEMA}.$1");
        List<String> contentAfterReplacement = PlainTextUtils.doReplace(fileContent,replacementPatterns);
        FileUtils.writeStringsToFile(contentAfterReplacement, outputFileName);
    }
}
