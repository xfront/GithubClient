package com.ddmeng.githubclient.account;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CodeParserTest {

    private CodeParser codeParser = new CodeParser();


    @Test
    public void testParseCodeFromUrl() throws Exception {
        String code = codeParser.parseCode("https://github.com/?code=234");

        assertThat(code).isEqualTo("234");
    }

    @Test
    public void testReturnNullIfNoCodeInUrl() throws Exception {
        String code = codeParser.parseCode("https://github.com/?aaa=2f5ccaff585b172c4711");

        assertThat(code).isNull();
    }

    @Test
    public void testReturnEmptyIfCodeIsEmpty() throws Exception {
        String code = codeParser.parseCode("https://https://developer.android.com/index.html/?code=");

        assertThat(code).isEmpty();
    }

    @Test
    public void testWhenCodeIsNotFirstParameter() throws Exception {
        String code = codeParser.parseCode("https://https://developer.android.com/index.html/?aaa=ccc&code=123");

        assertThat(code).isEqualTo("123");
    }
}