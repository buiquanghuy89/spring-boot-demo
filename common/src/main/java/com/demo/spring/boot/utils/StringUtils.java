package com.demo.spring.boot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bqhuy on 12/9/2016.
 */
public final class StringUtils {
    private Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * alphabeUpCaseNumber.
     */
    private static String alphabeUpCaseNumber = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Creates a new instance of StringUtils
     */
    private StringUtils() {
    }

    /**
     * method compare two string
     *
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static boolean compareString(String str1, String str2) {
        if (str1 == null) {
            str1 = "";
        }
        if (str2 == null) {
            str2 = "";
        }

        if (str1.equals(str2)) {
            return true;
        }
        return false;
    }

    /*
     * Check String that containt only AlphabeUpCase and Number Return True if
     * String was valid, false if String was not valid
     */
    public static boolean checkAlphabeUpCaseNumber(String value) {
        boolean result = true;
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (alphabeUpCaseNumber.indexOf(temp) == -1) {
                result = false;
                return result;
            }
        }
        return result;
    }

    public static List<String> splitObjectId(String value) {
        Pattern MY_PATTERN = Pattern.compile("\\[[a-zA-Z0-9 _-]+\\]");
        Matcher matcher = MY_PATTERN.matcher(value);

        List<String> listMatches = new ArrayList<String>();

        while (matcher.find()) {
            String tmp = matcher.group(0);
            tmp = tmp.replace("[", "");
            tmp = tmp.replace("]", "");
            listMatches.add(tmp.trim());
        }

        return listMatches;
    }

    public static List<String> splitFieldName(String value) {
        Pattern MY_PATTERN = Pattern.compile("\\{[a-zA-Z0-9 >_-]+\\}");
        Matcher matcher = MY_PATTERN.matcher(value);

        List<String> listMatches = new ArrayList<String>();

        while (matcher.find()) {
            listMatches.add(matcher.group(0));
        }

        return listMatches;
    }

    public static boolean validString(String temp) {
        if (temp == null || temp.trim().equals("")) {
            return false;
        }
        return true;
    }

    public static String escapeHTML(String str) {
        if (validString(str)) {
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("\"", "&quot;");
            str = str.replaceAll("\'", "&#039;");
        }
        return str;
    }

    public static String escapeHTMLString(String str) {
        if (validString(str)) {
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
            str = str.replaceAll("\"", "&quot;");
            str = str.replaceAll("\'", "&#039;");
            // str = str.replaceAll("\r", "<BR>&nbsp;&nbsp;");
            // str = str.replaceAll("\n", "<BR>&nbsp;&nbsp;");
            str = str.replaceAll("\r\n", "<BR>&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        return str;
    }

    public static String getPartOfString(String str, Long part) {
        if (str != null && !str.isEmpty()) {
            String[] arr = str.split("/");
            if (arr.length >= part.intValue()) {
                return arr[part.intValue() - 1];
            }
        }
        return "";

    }

    public static boolean isNotNull(String value) {
        if (value == null || value.toString().trim().length() <= 0) {
            return false;
        }
        return true;
    }

    public static byte[] convertToUTF8(byte[] original) {
        String str = null;
        try {
            str = new String(original, Charset.forName("UTF-8"));
            return str.getBytes(Charset.forName("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return original;
    }

    public static String bytesConvertToUTF8(byte[] original) {
        String str = null;
        try {
            str = new String(original, Charset.forName("UTF-8"));
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }

    public static byte[] convertToUTF8(String xml) {
        byte[] out;
        try {
            out = xml.getBytes(Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            out = xml.getBytes();
        }
        return out;
    }

    public static String getStringByListStr(List<String> lst) {
        StringBuilder sb = new StringBuilder();
        if (lst != null && lst.size() > 0) {
            int sizeMinus = lst.size() - 1;
            for (int i = 0; i < sizeMinus; i++) {
                sb.append(lst.get(i));
                sb.append(",");
            }
            sb.append(lst.get(sizeMinus));
        }
        return sb.toString();
    }

    public static String getStringByList(List<Integer> lst) {
        StringBuilder sb = new StringBuilder();
        if (lst != null && lst.size() > 0) {
            int sizeMinus = lst.size() - 1;
            for (int i = 0; i < sizeMinus; i++) {
                sb.append(lst.get(i));
                sb.append(",");
            }
            sb.append(lst.get(sizeMinus));
        }
        return sb.toString();
    }

    public static String convertStringRegex(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll("đ", "d");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
