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

        List<String> queries =Changers.findAllQueries("/Users/azhelezny/projects/splice_machine/test-jmeter/src/test/jmeter/order-by.jmx");
        FileUtils.writeStringsToFile(queries,"/Users/azhelezny/projects/jmx-smart-replacer/target/order-by.sql");
      /*  String dirName = "/Users/azhelezny/projects/splice_machine/test-jmeter/src/test/jmeter";
        String fileShortName = "window-functions-escalations.pish";
        String fileName = fileShortName + ".jmx";
        String filePath = dirName + "/" + fileName;


        Changers.removeAllQueryNumbersFromTestName(filePath);
        Changers.removeAllComments(filePath);

        Changers.addQuerryNumberToTestName(filePath);
        Changers.addCommentWithQueryNumber(filePath);
        Changers.shrinkSamplerNames(filePath, filePath);*/
    }
}
