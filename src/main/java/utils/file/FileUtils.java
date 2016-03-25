package utils.file;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel on 3/23/16.
 */
public class FileUtils {

    public static List<String> readFileToList(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line, ls = System.getProperty("line.separator");
        List<String> fileLines = new ArrayList<String>();
        while ((line = reader.readLine()) != null)
            fileLines.add(line + ls);
        return fileLines;
    }


    public static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public static void writeStringsToFile(String str, String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (file.exists())
            file.delete();
        PrintWriter fw = null;
        try {
            fw = new PrintWriter(new BufferedWriter(new FileWriter(file, file.exists())));
            fw.println(str);
        } finally {
            assert fw != null;
            fw.close();
        }
    }
}