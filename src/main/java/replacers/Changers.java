package replacers;

import utils.file.FileUtils;
import utils.text.PlainTextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel on 3/23/16.
 */
public class Changers {
    public final static String createTableLexeme = "create[\\ ]+table[\\ ]+([\\w]+)";
    public final static String createTriggerLexeme = "create[\\ ]+trigger[\\ ]+([\\w]+)";
    public final static String createViewLexeme = "create[\\ ]+view[\\ ]+([\\w]+)";

    public final static String nonQutedContentReplacementPattern = "([\\ ]+)(%s)([^\\w]+)";


    public final static String queryComment = "--QUERY X";

    public final static String queryFindRegex = "(<stringProp[\\ ]+name[\\ ]*=[\\ ]*\\\"query\\\">)(.*)";

    public final static int samplerNameMaxLength = 100;
    public final static String samplerNameRegex = "(?si)(JDBCSampler)(.*?)(testname\\s*=\\s*\")(.*?)(\")(.*?)(JDBCSampler)";

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
                if (i % 500 == 0) {
                    System.out.println("Queries changed: " + i);
                }
            } else {
                end = false;
                System.out.println("Queries changed: " + i);
            }
        }
        FileUtils.writeStringsToFile(test, path);
    }

    public static void removeAllComments(String path) throws IOException {
        String test = FileUtils.readFile(path);
        test = test.replaceAll("--QUERY\\s*\\d+\\s*\\n", "");
        FileUtils.writeStringsToFile(test, path);
        System.out.println("All comments removed");
    }

    public static void removeAllQueryNumbersFromTestName(String path) throws IOException {
        String test = FileUtils.readFile(path);
        test = test.replaceAll("\"\\s*\\[\\d+\\]", "\"");
        System.out.println("All QueryNumbers removed");
        FileUtils.writeStringsToFile(test, path);
    }


    public static void addQuerryNumberToTestName(String path) throws IOException {
        String test = FileUtils.readFile(path);
        test = test.replace("${", "DOLLAR");
        test = test.replaceAll("(?si)(<JDBCSampler.*?testname=\")([^\"]*)(.*?<stringProp name=\"query\">)(.*?)(</stringProp)(.*?</JDBCSampler)", "$1[]$4$3$4$5$6");
        for (int i = 1; test.contains("[]"); i++) {
            test = test.replaceFirst("\\[\\]", "[" + i + "]");
        }
        test = test.replace("DOLLAR", "${");
        FileUtils.writeStringsToFile(test, path);
    }


    public static void shrinkSamplerNames(String inputFilePath, String outputFilePath) throws IOException {
        int replacementsCount = 0;
        String replacementFormat = "sampler name pish drish";
        Map<String, String> replacements = new HashMap<String, String>();
        String fileContent = FileUtils.readFile(inputFilePath);
        Matcher matcher = Pattern.compile(samplerNameRegex).matcher(fileContent);
        while (matcher.find()) {
            replacements.put(replacementFormat + replacementsCount, matcher.group(4));
            replacementsCount += 1;
        }
        fileContent = fileContent.replaceAll(samplerNameRegex, "$1$2$3" + replacementFormat + "$5$6$7");
        for (int i = 0; i < replacementsCount; i++) {
            fileContent = fileContent.replaceFirst(replacementFormat,
                    PlainTextUtils.getTextForName(replacements.get(replacementFormat + String.valueOf(i)), samplerNameMaxLength).replace("$", "\\$"));
        }
        FileUtils.writeStringsToFile(fileContent, outputFilePath);
        System.out.println("Processed strings: " + replacementsCount);
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
        lexemes.add(createTableLexeme);
        lexemes.add(createTriggerLexeme);
        lexemes.add(createViewLexeme);
        List<String> namesToReplace = PlainTextUtils.findAllOccurrencesWithRegexp(fileContent, lexemes);
        Map<String, String> replacementPatterns = new HashMap<String, String>();
        for (String name : namesToReplace)
            replacementPatterns.put(String.format(nonQutedContentReplacementPattern, name), "$1\\${SCHEMA}.$2$3");
        return PlainTextUtils.doReplace(fileContent, replacementPatterns);
    }

    public static void generateExplains(String path) throws IOException {
        String test = FileUtils.readFile(path);

    }
}
