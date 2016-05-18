package utils.text;

import utils.file.FileUtils;

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
public class PlainTextUtils {

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

    public static List<String> doSpeedReplace(List<String> content, Map<String, String> regexps){
        long replacements = 0;
        List<String> result = new ArrayList<String>();
        for (String line : content) {
            String resString = line;
            for (Map.Entry<String, String> regexp : regexps.entrySet()) {
                String replacement = regexp.getKey();
                String replacer = regexp.getValue();
                Pattern pattern = Pattern.compile(replacement, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(resString);
                if (!matcher.find()) continue;
                replacements += 1;
                resString = matcher.replaceAll(replacer);
            }
            result.add(resString);
        }
        System.out.println("Replacements done: " + replacements);
        return result;
    }

    public static List<String> doReplace(List<String> content, Map<String, String> regexps) {
        long replacements = 0;
        List<String> result = new ArrayList<String>();
        for (String line : content) {
            String resString = line;
            for (Map.Entry<String, String> regexp : regexps.entrySet()) {
                String replacement = regexp.getKey();
                String replacer = regexp.getValue();
                Pattern pattern = Pattern.compile(replacement, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(resString);
                if (!matcher.find()) continue;
                replacements += 1;
                resString = matcher.replaceAll(replacer);
            }
            result.add(resString);
        }
        System.out.println("Replacements done: " + replacements);
        return result;
    }

    public static String getMathersGroup(String where, String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(where);
        String result = "";
        if (matcher.find()) {
            result = matcher.group(group);
        }
        return result;
    }

    public static List<String> splitToWords(String arg) {
        String prepared = arg.replaceAll("\\|", "  ");
        prepared = prepared.replaceAll("\\t", "    ");
        prepared = prepared.replaceAll("[\\ ]+\\n", "\n");
        List<String> result = new ArrayList<String>();
        Pattern regex = Pattern.compile("([^\\ ]+)");
        Matcher matcher = regex.matcher(prepared);
        while (matcher.find())
            result.add(matcher.group(0));
        return result;
    }

    public static Map<String, String> compareLinesIgnoreCaseAndWhitespace(String line1, String line2) {
        Map<String, String> result = new HashMap<String, String>();
        List<String> lines1 = splitToWords(line1);
        List<String> lines2 = splitToWords(line2);

        int size = (lines1.size() > lines2.size()) ? lines2.size() : lines1.size();
        if (lines1.size() != size)
            result.put("line1 has extra words", String.valueOf(lines1.size() - size));
        if (lines2.size() != size)
            result.put("line2 has extra words", String.valueOf(lines2.size() - size));

        for (int i = 0; i < size; i++) {
            if (!lines1.get(i).toUpperCase().equals(lines2.get(i).toUpperCase()))
                result.put("column " + String.valueOf(i + 1), "[" + lines1.get(i) + "] <> [" + lines2.get(i) + "]");
        }
        return result;
    }
}
