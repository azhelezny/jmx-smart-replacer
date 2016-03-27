package sandbox;

import replacers.Changers;
import utils.file.FileUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author Andrey Zhelezny
 *         Date: 3/25/16
 */
public class AlternativeMain {
    public static void main(String[] args) throws IOException {
        String fileName = "/Users/azhelezny/Desktop/triggers/done/advanced_operations.sql.jmx";
        String outputFileName = "/Users/azhelezny/Desktop/triggers/done/advanced_operations.new.jmx";
        List<String> fileContent = FileUtils.readFileToList(fileName);
        List<String> newFileContent = Changers.addCommentWithQueryNumberAlt(fileContent);
        newFileContent = Changers.addSchemas(newFileContent);
        FileUtils.writeStringsToFile(newFileContent,outputFileName);
    }
}
