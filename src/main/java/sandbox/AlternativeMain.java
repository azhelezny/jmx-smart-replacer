package sandbox;

import utils.file.FileUtils;
import utils.text.PlainTextUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Andrey Zhelezny
 *         Date: 3/25/16
 */
public class AlternativeMain {


    public static void main(String[] args) throws IOException {
        String file1 = "/Users/azhelezny/Desktop/comparisons/query40.mas";
        String file2 = "/Users/azhelezny/Desktop/comparisons/query40.jmx";
        List<String> file1Lines = FileUtils.readFileToList(file1);
        List<String> file2Lines = FileUtils.readFileToList(file2);

        //if (file1Lines.size() != file2Lines.size())
          //  throw new RuntimeException("files have different numbers of lines");

        for (int i = 0; i < file2Lines.size(); i++) {
            Map<String, String> comparisonResult = PlainTextUtils.compareLinesIgnoreCaseAndWhitespace(file1Lines.get(i), file2Lines.get(i));
            if (comparisonResult.size() != 0)
                System.out.println("Line Number [" + String.valueOf(i + 1) + "]:" + comparisonResult);
        }

        //List<String> files = FileUtils.getFileNamesFromDir(dirName, "_test.jmx");
        //List<String> dataTypes = new ArrayList<String>();




        /*Pattern pattern = Pattern.compile("^([A-Z_]+)_");
        for (String filePath : files) {
            Matcher matcher = pattern.matcher(new File(filePath).getName());
            if (matcher.find())
                dataTypes.add(matcher.group(1));
            else
                dataTypes.add(null);
        }


        for (int i = 0; i < files.size(); i++) {
            List<String> fileContent = FileUtils.readFileToList(files.get(i));
           // fileContent = Changers.addCommentWithQueryNumberAlt(fileContent);
           // fileContent = Changers.addSchemas(fileContent);
            if (dataTypes.get(i) != null) {
                Map<String, String> replacer = new HashMap<String, String>();
                //replacer.put("\\$\\{SCHEMA\\}", "\\${PRIMARY_KEYS_MULTIPLE_COLUMNS_" + dataTypes.get(i) + "_MODIFICATION_SCHEMA}");

                //replacer.put("run_queries", "Running: ROW_NUMBER " + dataTypes.get(i));
                replacer.put("Generate Summary Results", "Running: SUM " + dataTypes.get(i));
                fileContent = PlainTextUtils.doReplace(fileContent, replacer);
            }
            FileUtils.writeStringsToFile(fileContent, files.get(i));
        }

        /*List<String> fileContent = FileUtils.readFileToList(fileName);
        fileContent = Changers.addSchemas(fileContent);
        FileUtils.writeStringsToFile(fileContent, fileName);*/


    }
}
