package com.lichy.algorithm.stack;

import java.util.Stack;

/**
 * 使用stack进行波兰表达式计算
 *
 * @author lichy
 */
public class ReversePolishNotationTest {

    /**
     * 待计算的逆波兰表达式
     */
    private static String[] array = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            String temp = array[i];
            if ("+".equals(temp)) {
                int first = Integer.parseInt(stack.pop());
                int second = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(first + second));
            } else if ("-".equals(temp)) {
                int first = Integer.parseInt(stack.pop());
                int second = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(second - first));
            } else if ("*".equals(temp)) {
                int first = Integer.parseInt(stack.pop());
                int second = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(first * second));
            } else if ("/".equals(temp)) {
                int first = Integer.parseInt(stack.pop());
                int second = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(second / first));
            } else {
                stack.push(temp);
            }
        }
        System.out.println(stack.pop());
    }

}
