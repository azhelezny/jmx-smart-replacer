package utils.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andrey Zhelezny
 *         Date: 3/25/16
 */
public class PlainTextUtils {
    public static String createTableLexeme = "create[\\ ]+table[\\ ]+([\\w]+)";
    public static String createTriggerLexeme = "create[\\ ]+trigger[\\ ]+([\\w]+)";

    public static String nonQutedContentReplacementPattern = "(%s)";


    public static List<String> findAllOccurrencesWithRegexp(List<String> fileLines, List<String> lexemesRegex) {
        List<String> result = new ArrayList<String>();
        List<Pattern> patterns = new ArrayList<Pattern>();
        for (String lexeme : lexemesRegex)
            patterns.add(Pattern.compile(lexeme, Pattern.MULTILINE + Pattern.CASE_INSENSITIVE));

        for (String line : fileLines)
            for (Pattern pattern : patterns) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find())
                    if (matcher.groupCount() != 0)
                        if (!result.contains(matcher.group(1).toUpperCase()))
                            result.add(matcher.group(1).toUpperCase());
            }
        return result;
    }

    public static List<String> doReplace(List<String> content, Map<String, String> regexps) {
        List<String> result = new ArrayList<String>();
        for (String line : content) {
            String resString = line;
            for (Map.Entry<String, String> regexp : regexps.entrySet()) {
                String replacement = regexp.getKey();
                String replacer = regexp.getValue();
                Pattern pattern = Pattern.compile(replacement, Pattern.MULTILINE + Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(resString);
                if (!matcher.find()) continue;
                resString = matcher.replaceAll(replacer);
            }
            result.add(resString);
        }
        return result;
    }
}
