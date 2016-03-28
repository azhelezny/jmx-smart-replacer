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
        String fileName = "/Users/azhelezny/projects/splice_machine/test/jmeter/src/test/jmeter/plain/functions/window/first_value/BOOLEAN_test.jmx";
        String dirName = "/Users/azhelezny/projects/splice_machine/test/jmeter/src/test/jmeter/plain/functions/window/first_value";
        List<String> files = FileUtils.getFileNamesFromDir(dirName, "_test.jmx");


        /*for (String filePath : files) {
            List<String> fileContent = FileUtils.readFileToList(filePath);
            fileContent = Changers.addCommentWithQueryNumberAlt(fileContent);
            //fileContent = Changers.addSchemas(fileContent);
            FileUtils.writeStringsToFile(fileContent, filePath);
        }*/

        List<String> fileContent = FileUtils.readFileToList(fileName);
        fileContent = Changers.addSchemas(fileContent);
        FileUtils.writeStringsToFile(fileContent, fileName);


    }
}
