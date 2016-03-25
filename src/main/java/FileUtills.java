import java.io.*;

/**
 * @author Pavel on 3/23/16.
 */
public class FileUtills {
    public static String readFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while( ( line = reader.readLine() ) != null ) {
                stringBuilder.append( line );
                stringBuilder.append( ls );
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
