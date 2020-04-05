package com.lichy.algorithm.stack;

import java.util.Stack;

/**
 * 利用stack来判断括号是否匹配。包括{[(
 *
 * @author lichy
 */
public class BracketMatchTest {

    public static void main(String[] args) {
        String value = "{}{}{}";
        System.out.println("字符串【" + value + "】是否闭合：" + isMatch(value));
        value = "[]";
        System.out.println("字符串【" + value + "】是否闭合：" + isMatch(value));
        value = "{}}";
        System.out.println("字符串【" + value + "】是否闭合：" + isMatch(value));
        value = "}}}";
        System.out.println("字符串【" + value + "】是否闭合：" + isMatch(value));
        value = "{123}]";
        System.out.println("字符串【" + value + "】是否闭合：" + isMatch(value));
        value = "12{123}[](2)123";
        System.out.println("字符串【" + value + "】是否闭合：" + isMatch(value));
    }

    /**
     * 判断字符串是否匹配
     *
     * @param str 字符串
     * @return 是否匹配
     */
    private static boolean isMatch(String str) {
        if (str == null || str.length() < 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('{' == c || '[' == c || '(' == c) {
                stack.push(c);
            } else if ('}' == c) {
                if (stack.isEmpty() || '{' != stack.peek()) {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (']' == c) {
                if (stack.isEmpty() || '[' != stack.peek()) {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (')' == c) {
                if (stack.isEmpty() || '(' != stack.peek()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }
}
