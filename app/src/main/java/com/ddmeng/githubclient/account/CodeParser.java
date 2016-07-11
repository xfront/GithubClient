package com.ddmeng.githubclient.account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeParser {
    private static final Pattern CODE_URL_PATTERN = Pattern.compile("^http.*[?|&]code=(.*)$");

    public String parseCode(String input) {
        Matcher matcher = CODE_URL_PATTERN.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
