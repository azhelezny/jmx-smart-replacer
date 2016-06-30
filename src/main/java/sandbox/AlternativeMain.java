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

        String dirName = "/Users/azhelezny/projects/splice_machine/test-jmeter/src/test/jmeter";
        String fileShortName = "transactions1";
        String fileName = fileShortName + ".jmx";
        String filePath = dirName + "/" + fileName;
        //Changers.removeAllComments(filePath);
        //Changers.removeAllQueryNumbersFromTestName(filePath);
        //Changers.addQuerryNumberToTestName(filePath, true);
        //Changers.addCommentWithQueryNumber(filePath);
        List<String> fileContent = FileUtils.readFileToList(filePath);
        fileContent = Changers.addCommentWithQueryNumberAlt(fileContent);
        FileUtils.writeStringsToFile(fileContent,filePath);
    }
}
