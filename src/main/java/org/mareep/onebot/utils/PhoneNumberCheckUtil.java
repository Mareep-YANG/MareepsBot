package org.mareep.onebot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberCheckUtil {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // 使用正则表达式匹配手机号码的格式
        String regex = "^1[3456789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        // 判断是否匹配
        return matcher.matches();
    }
}
