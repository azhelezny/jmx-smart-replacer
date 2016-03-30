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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andrey Zhelezny
 *         Date: 3/25/16
 */
public class AlternativeMain {
    public static void main(String[] args) throws IOException {
        String fileName = "/Users/azhelezny/projects/splice_machine/test/jmeter/src/test/jmeter/plain/functions/window/first_value/BOOLEAN_test.jmx";
        String dirName = "/Users/azhelezny/projects/splice_machine/test/jmeter/src/test/jmeter/plain/constraints/primary_key/multiple_columns/modification";
        List<String> files = FileUtils.getFileNamesFromDir(dirName, "_test.jmx");
        List<String> dataTypes = new ArrayList<String>();
        Pattern pattern = Pattern.compile("^([A-Z_]+)_");
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
                replacer.put("Generate Summary Results", "Running: primary key " + dataTypes.get(i));
                fileContent = PlainTextUtils.doReplace(fileContent, replacer);
            }
            FileUtils.writeStringsToFile(fileContent, files.get(i));
        }

        /*List<String> fileContent = FileUtils.readFileToList(fileName);
        fileContent = Changers.addSchemas(fileContent);
        FileUtils.writeStringsToFile(fileContent, fileName);*/


    }
}
