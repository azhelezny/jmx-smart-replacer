package sandbox;

import replacers.Changers;

import java.io.IOException;

/**
 * @author Andrey Zhelezny
 *         Date: 3/25/16
 */
public class AlternativeMain {


    public static void main(String[] args) throws IOException {

        String dirName = "/Users/azhelezny/projects/splice_machine/test-jmeter/src/test/jmeter";
        String fileShortName = "_joins1";
        String fileName = fileShortName + ".jmx";
        String outFileName = "_" + fileShortName + ".jmx";
        String filePath = dirName + "/" + fileName;
        String outFilePath = dirName + "/" + outFileName;
        //Changers.removeAllComments(filePath);
        //Changers.removeAllQueryNumbersFromTestName(filePath);
        //Changers.addQuerryNumberToTestName(filePath);
        //Changers.addCommentWithQueryNumber(filePath);
        //List<String> fileContent = FileUtils.readFileToList(filePath);
        Changers.shrinkSamplerNames(filePath,outFilePath);
        //FileUtils.writeStringsToFile(fileContent,filePath);
    }
}
