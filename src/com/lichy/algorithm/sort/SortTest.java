package com.lichy.algorithm.sort;

import java.util.Arrays;

/**
 * 排序算法测试类
 *
 * @author lichy
 */
public class SortTest {
    /**
     * 待排序数组
     */
    private static int[] arrayToSort = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};


    public static void main(String[] args) {
        System.out.println("插入排序后结果为：");
        printArray(insertSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
        System.out.println("shell排序后结果为：");
        printArray(shellSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
        System.out.println("选择排序后结果为：");
        printArray(selectionSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
        System.out.println("冒泡排序后结果为：");
        printArray(bubbleSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
    }

    /**
     * 冒泡排序
     *
     * @param input 输入
     * @return 输出
     */
    private static int[] bubbleSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[j - 1]) {
                    int temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }

    /**
     * 选择排序
     *
     * @param input 输入
     * @return 输出
     */
    private static int[] selectionSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            int min = input[i];
            int index = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < min) {
                    min = input[j];
                    index = j;
                }
            }
            if (index != i) {
                int temp = input[index];
                input[index] = input[i];
                input[i] = temp;
            }
        }
        return input;
    }

    /**
     * shell排序
     *
     * @param input 输入
     * @return 排序后的数组
     */
    private static int[] shellSort(int[] input) {
        for (int gap = input.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < input.length; i++) {
                int temp = input[i];
                int index = i - gap;
                while (index >= 0 && temp < input[index]) {
                    input[index + gap] = input[index];
                    index -= gap;
                }
                input[gap + index] = temp;
            }
        }
        return input;
    }

    /**
     * 对输入的数组进行插入排序
     *
     * @param input 输入数组
     * @return 返回数组
     */
    private static int[] insertSort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            int temp = input[i];
            int index = i - 1;
            while (index >= 0 && input[index] > temp) {
                input[index + 1] = input[index];
                index--;
            }
            input[index + 1] = temp;
        }
        return input;
    }

    /**
     * 打印数组
     *
     * @param input 数组
     */
    private static void printArray(int[] input) {
        if (input != null) {
            for (int i = 0; i < input.length; i++) {
                if (i != input.length - 1) {
                    System.out.print(input[i] + "，");
                } else {
                    System.out.println(input[i]);
                }
            }
        }
    }

}
