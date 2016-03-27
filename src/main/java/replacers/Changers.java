package replacers;

import utils.file.FileUtils;
import utils.text.PlainTextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pavel on 3/23/16.
 */
public class Changers {

    public final static String queryComment = "--QUERY X";

    public final static String queryFindRegex = "(<stringProp[\\ ]+name[\\ ]*=[\\ ]*\\\"query\\\">)(.*)";

    public final static String queryWithoutGlobalNumberRegex = "(<JDBCSampler)(.*)(testname=\\\")([\\ ]*[\\w]+)";
    public final static String queryWithoutGlobalNumberReplacer = "$1$2$3[1] $4";

    public final static String queryGlobalNumberRegexp = "(<JDBCSampler)(.*)(testname=\\\")(\\[\\d+\\][\\ ]*)([^\\\"]*)(\\\")";
    public final static String queryGlobalNumberRegexpReplacementPattern = "$1$2$3\\[" + queryComment + "\\] $5$6";

    public final static String queryNumberRegexExtractor = "(<JDBCSampler)(.*)(testname=\\\")(\\[[^\\d]*)(\\d+)";
    public final static String queryNumberRegexReplacer = "$1$2$3[$5";

    public static void addCommentWithQueryNumber(String path) throws IOException {
        String test = FileUtils.readFile(path);
        String queryStart = "<stringProp name=\"query\">";
        test = test.replace(queryStart, queryStart + "--QUERY X" + "\n");
        Boolean end = true;
        for (int i = 1; end; i++) {
            if (test.contains("--QUERY X")) {
                test = test.replaceFirst("--QUERY X", "--QUERY " + i);
            } else {
                end = false;
            }
            System.out.println("Queries changed: " + i);
        }
        FileUtils.writeStringsToFile(test, path);
    }

    public static List<String> addCommentWithQueryNumberAlt(List<String> fileContent) throws IOException {
        Map<String, String> regexps = new HashMap<String, String>();

        regexps.put(queryWithoutGlobalNumberRegex, queryWithoutGlobalNumberReplacer);
        regexps.put(queryGlobalNumberRegexp, queryGlobalNumberRegexpReplacementPattern);
        regexps.put(queryFindRegex, "$1" + queryComment + "\n$2");
        List<String> result = new ArrayList<String>(), newFileContent = PlainTextUtils.doReplace(fileContent, regexps);
        int i = 2;
        for (String line : newFileContent) {
            if (line.contains(queryComment)) {
                line = line.replace(queryComment, "--QUERY " + i / 2);
                i++;
            }
            result.add(line);
        }
        regexps.clear();
        regexps.put(queryNumberRegexExtractor, queryNumberRegexReplacer);
        result = PlainTextUtils.doReplace(result, regexps);
        return result;
    }

    public static List<String> addSchemas(List<String> fileContent) {
        List<String> lexemes = new ArrayList<String>();
        lexemes.add(PlainTextUtils.createTableLexeme);
        lexemes.add(PlainTextUtils.createTriggerLexeme);
        List<String> namesToReplace = PlainTextUtils.findAllOccurrencesWithRegexp(fileContent, lexemes);
        Map<String, String> replacementPatterns = new HashMap<String, String>();
        for (String name : namesToReplace)
            replacementPatterns.put(String.format(PlainTextUtils.nonQutedContentReplacementPattern, name), "\\${SCHEMA}.$1");
        return PlainTextUtils.doReplace(fileContent, replacementPatterns);
    }

    public static void generateExplains(String path) throws IOException {
        String test = FileUtils.readFile(path);

    }
}
