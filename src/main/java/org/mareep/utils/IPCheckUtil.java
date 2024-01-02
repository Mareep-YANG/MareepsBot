package org.mareep.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPCheckUtil {

    private static final String IP_ADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static final Pattern pattern = Pattern.compile(IP_ADDRESS_PATTERN);

    public static boolean validateIP(String ip) {
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
