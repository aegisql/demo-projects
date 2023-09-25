package com.aegisql.search_engine.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String firstWords(CharSequence phrase, int words) {

        String pattern = "(?:\\W|^)(\\w+)(?:\\W+(\\w+)){0," + (words - 1) + "}(?=\\W|$)";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(phrase);
        if(matcher.find()) {
            return matcher.group().trim();
        }

        return phrase.toString();
    }

    public static String lastWords(CharSequence phrase, int words) {
        StringBuilder sb = new StringBuilder(phrase);
        String s = firstWords(sb.reverse(), words);
        sb.setLength(0);
        sb.append(s);
        return sb.reverse().toString();
    }

}
