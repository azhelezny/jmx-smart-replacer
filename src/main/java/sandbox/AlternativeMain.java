package sandbox;

import replacers.Changers;
import utils.file.FileUtils;
import utils.text.PlainTextUtils;

import java.io.File;
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
/*        String file1 = "/Users/azhelezny/Desktop/1.mas";
        String file2 = "/Users/azhelezny/Desktop/1.jmx";
        List<String> file1Lines = FileUtils.readFileToList(file1);
        List<String> file2Lines = FileUtils.readFileToList(file2);

        //if (file1Lines.size() != file2Lines.size())
        //  throw new RuntimeException("files have different numbers of lines");

        for (int i = 0; i < file2Lines.size(); i++) {
            Map<String, String> comparisonResult = PlainTextUtils.compareLinesIgnoreCaseAndWhitespace(file1Lines.get(i), file2Lines.get(i));
            if (comparisonResult.size() != 0)
                System.out.println("Line Number [" + String.valueOf(i + 1) + "]:" + comparisonResult);
        }*/

        /*
        String dirName = "/Users/azhelezny/projects/splice_machine/test-jmeter/src/test/jmeter/plain/comparisons";
        List<String> files = FileUtils.getFileNamesFromDir(dirName, ".");
        List<String> fileNames = new ArrayList<String>();

        for (String filePath : files)
            fileNames.add(new File(filePath).getName().replace(".jmx", "").toUpperCase());

        for (int i = 0; i < files.size(); i++) {
            List<String> fileContent = FileUtils.readFileToList(files.get(i));
            Map<String, String> replacer = new HashMap<String, String>();

            fileContent =Changers.addSchemas(fileContent);
            replacer.put("\\$\\{__P\\(clusterRegionserver\\)\\}", "localhost");
            String schemaName = "COMPARISONS_" + fileNames.get(i) + "_SCHEMA";
            replacer.put("\\$\\{SCHEMA\\}", "\\${"+schemaName+"}");
            replacer.put("(Argument\\.name\">)SCHEMA(</stringProp>)", "$1"+schemaName+"$2");
            fileContent = PlainTextUtils.doReplace(fileContent, replacer);
            fileContent = Changers.addCommentWithQueryNumberAlt(fileContent);
            FileUtils.writeStringsToFile(fileContent, dirName + "/A_" + fileNames.get(i) + "_edited.jmx");
        }

*/
        String dirName = "/Users/azhelezny/projects/splice_machine/test-jmeter/src/test/jmeter";
        String fileShortName = "plain-clauses-order_by";
        String fileName = fileShortName+".jmx";
        //String fileName = "createtbls_hh.sql.jmx";
        String filePath = dirName + "/" + fileName;
        List<String> fileContent = FileUtils.readFileToList(filePath);
        //fileContent = Changers.addSchemas(fileContent);
        fileContent = Changers.addCommentWithQueryNumberAlt(fileContent);
        //Map <String,String> replacer = new HashMap<String, String>();
        //replacer.put("\\$\\{SCHEMA\\}", "\\${"+fileShortName.toUpperCase()+"_SCHEMA}");
        //fileContent = PlainTextUtils.doReplace(fileContent, replacer);
        FileUtils.writeStringsToFile(fileContent, dirName+"/"+"_"+fileName);
    }
}
