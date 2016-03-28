package utils.file;

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

    public static void writeStringsToFile(List<String> strings, String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists())
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();
        PrintWriter fw = null;
        try {
            fw = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
            for (String fileLine : strings)
                fw.print(fileLine);
        } finally {
            assert fw != null;
            fw.close();
        }
    }

    public static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String line, ls = System.getProperty("line.separator");

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
        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();
        if (file.exists())
            //noinspection ResultOfMethodCallIgnored
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

    public static List<String> getFileNamesFromDir(String dirName, final String mask){
        File dir = new File(dirName);
        File [] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.contains(mask);
            }
        });
        List<String> result = new ArrayList<String>();
        for (File file : files)
            result.add(file.getAbsolutePath());
        return result;
    }
}
