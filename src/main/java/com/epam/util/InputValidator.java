package com.epam.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
//    private static final String EMAIL_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
//            "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|" +
//            "biz|info|mobi|name|aero|asia|jobs|museum)\\b";
    private static final String LOGIN_REGEX = "/^[a-z0-9_-]{5,32}$/";

    public static boolean login(String login){
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }
}
