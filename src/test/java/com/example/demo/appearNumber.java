package com.example.demo;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by koreyoshi on 2018/2/1.
 */
public class appearNumber {
    @Test
    public void test() {
        String srcText = "select * from xxx where id = ? and name = ?";
        String findText = "\\?";
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        System.out.println("the count is:[ " + count + " ]");
    }
}
