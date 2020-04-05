package com.lichy.algorithm.sort;

import java.util.*;

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
        System.out.println("堆排序后结果为：");
        printArray(heapSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
        System.out.println("快速排序后结果为：");
        printArray(quickSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
        System.out.println("归并排序后结果为：");
        printArray(mergeSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
        System.out.println("计数排序后结果为：");
        printArray(countSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
        System.out.println("基数排序后结果为：");
        printArray(radixSort(Arrays.copyOf(arrayToSort, arrayToSort.length)));
    }

    /**
     * 基数排序
     * @param input 输入
     * @return 输出
     */
    private static int[] radixSort(int[] input) {
        // 最高两位数
        int maxDigit = 2;
        int divisor = 1;
        for (int i = 0; i < maxDigit; i++, divisor *= 10) {
            TreeMap<Integer, List> bucket = new TreeMap<>();
            for (int anInput : input) {
                int mod = (anInput / divisor) % 10;
                List<Integer> array = bucket.get(mod);
                if (bucket.get(mod) == null) {
                    array = new ArrayList<>();

                    bucket.put(mod, array);
                }
                array.add(anInput);
            }
            int index = 0;
            for (List<Integer> integers : bucket.values()) {
                for (Integer integer : integers) {
                    input[index++] = integer;
                }
            }
        }
        return input;
    }

    /**
     * 计数排序
     *
     * @param input 输入
     * @return 输出
     */
    private static int[] countSort(int[] input) {
        int[] result = new int[input.length];
        int low = input[0];
        int high = input[0];
        Map<Integer, Integer> count = new HashMap<>();
        for (int anInput : input) {
            if (anInput < low) {
                low = anInput;
            }
            if (anInput > high) {
                high = anInput;
            }
            count.merge(anInput, 1, (a, b) -> a + b);
        }
        int resultIndex = 0;
        while (low <= high) {
            Integer curCount = count.get(low);
            if (curCount != null) {
                while (curCount > 0) {
                    result[resultIndex] = low;
                    resultIndex++;
                    curCount--;
                }
            }
            low++;
        }
        return result;
    }

    /**
     * 归并排序
     *
     * @param input 输入
     * @return 输出
     */
    private static int[] mergeSort(int[] input) {
        if (input.length < 2) {
            return input;
        }
        return merge(mergeSort(Arrays.copyOfRange(input, 0, input.length / 2)), mergeSort(Arrays.copyOfRange(input, input.length / 2, input.length)));
    }

    /**
     * 合并两个数组
     *
     * @param mergeSort1 数组1
     * @param mergeSort2 数组2
     * @return 合并后的数组
     */
    private static int[] merge(int[] mergeSort1, int[] mergeSort2) {
        int[] result = new int[mergeSort1.length + mergeSort2.length];
        int arrayIndex1 = 0;
        int arrayIndex2 = 0;
        int index = 0;
        while (arrayIndex1 < mergeSort1.length && arrayIndex2 < mergeSort2.length) {
            if (mergeSort1[arrayIndex1] <= mergeSort2[arrayIndex2]) {
                result[index] = mergeSort1[arrayIndex1];
                index++;
                arrayIndex1++;
            } else {
                result[index] = mergeSort2[arrayIndex2];
                index++;
                arrayIndex2++;
            }
        }
        if (arrayIndex1 < mergeSort1.length) {
            for (; index < result.length; index++) {
                result[index] = mergeSort1[arrayIndex1];
                arrayIndex1++;
            }
        }
        if (arrayIndex2 < mergeSort2.length) {
            for (; index < result.length; index++) {
                result[index] = mergeSort2[arrayIndex2];
                arrayIndex2++;
            }
        }
        return result;
    }

    /**
     * 快速排序
     *
     * @param input 输入
     * @return 输出
     */
    private static int[] quickSort(int[] input) {
        return quickSort(input, 0, input.length - 1);
    }

    /**
     * 快速排序
     *
     * @param input 输入
     * @return 输出
     */
    private static int[] quickSort(int[] input, int start, int end) {
        if (start >= end) {
            return input;
        }
        int index = start + 1;
        for (int i = start + 1; i <= end; i++) {
            if (input[i] < input[start]) {
                swap(input, i, index);
                index++;
            }
        }
        if (index != start + 1) {
            swap(input, start, index - 1);
        }
        quickSort(input, start, index - 2);
        quickSort(input, index, end);
        return input;
    }

    /**
     * 堆排序
     *
     * @param input 输入
     * @return 排序后的队列
     */
    private static int[] heapSort(int[] input) {
        // 构造大底堆
        for (int i = input.length / 2; i >= 0; i--) {
            modifyHeap(input, i, input.length);
        }
        int remainLength = input.length;
        for (int i = input.length - 1; i > 0; i--) {
            swap(input, 0, i);
            remainLength--;
            modifyHeap(input, 0, remainLength);
        }
        return input;
    }

    /**
     * 将当前位置之后的元素堆进行调整，保证是个大底堆
     *
     * @param input  输入
     * @param i      当前位置
     * @param length 进行对调整的数组长度
     */
    private static void modifyHeap(int[] input, int i, int length) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < length && input[left] > input[largest]) {
            largest = left;
        }
        if (right < length && input[right] > input[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(input, i, largest);
            modifyHeap(input, largest, length);
        }
    }

    /**
     * 交换两个元素的位置
     *
     * @param input 输入数组
     * @param i     位置1
     * @param j     位置2
     */
    private static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    /**
     * 冒泡排序
     *
     * @param input 输入
     * @return 输出
     */
    private static int[] bubbleSort(int[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - i - 1; j++) {
                if (input[j + 1] < input[j]) {
                    swap(input, j, j + 1);
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
