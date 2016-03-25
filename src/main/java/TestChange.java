import java.io.IOException;

/**
 * @author Pavel on 3/23/16.
 */
public class TestChange {
    public static void addCommentWithQueryNumber(String path) throws IOException {
        String test = FileUtills.readFile(path);
        String queryStart = "<stringProp name=\"query\">";
        test=test.replace(queryStart, queryStart+"--QUERY X"+ "\n");
        Boolean end = true;
        for (int i=1; end; i++){
            if (test.contains("--QUERY X")){
                test=test.replaceFirst("--QUERY X", "--QUERY " + i);
            } else {
                end=false;
            }
            System.out.println("Queries changed: " + i);
        }
       FileUtills.writeStringsToFile(test, path);
    }

    public static void generateExplains(String path) throws IOException {
        String test = FileUtills.readFile(path);

    }
}
