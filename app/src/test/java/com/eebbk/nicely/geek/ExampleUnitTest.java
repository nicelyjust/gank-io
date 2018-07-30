package com.eebbk.nicely.geek;

import org.junit.Test;

import java.util.Arrays;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect()
            throws Exception {
        int[] array = {1, 2, 3, 6, 8, 9};
//        selectSort(array , array.length);
//        bubbleSort(array , array.length);
        // quickSort(array , 0 , array.length-1);
        // insertionSort(array);
        int insert = binarySort(array, 1);
        System.out.println("插入位置:" +insert);
        System.out.println(Arrays.toString(array));
//        System.out.println(Arrays.toString(array));
    }

    /**
     * 直接插入排序
     *
     * @param array
     */
    public void insertionSort(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            count++;
            for (int j = i + 1; j > 0; j--) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }

            }
        }
        System.out.println(count);
    }

    /**
     * 二分法
     *
     * @param array
     * @param insert
     * @return
     */
    public int binarySort(int[] array, int insert) {
        int count = 0;
        int min = 0;
        int max = array.length - 1;

        while (min <= max) {
            int mid = (min + max) >> 1;
            count++;
            if (array[mid] < insert) {
                min = mid + 1;
            } else if (array[mid] > insert) {
                max = mid - 1;
            } else {
                return mid;
            }
        }
        System.out.println(count);
        return -(min+1);
    }

    /**
     * 简单选择排序
     */
    public void selectSort(int[] array, int n) {
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] < array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }

            }
        }
        printArray(array);
    }

    /**
     * 二分法选择排序,每一趟选出最大和最小
     */
    public void selectHalfSort(int[] array, int n) {
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] < array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }

            }
        }
    }

    /**
     * 冒泡排序 排序思想 相邻位置两两比较,每一趟min或者max放在后面
     * (O(n平方) , 一般不用)
     */
    public void bubbleSort(int[] array, int n) {
        int temp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                /*printArray(array);
                System.out.println("");*/
            }
        }
    }

    /**
     * 快速排序
     */
    public void quickSort(int[] array, int r, int l) {
        if (r >= l) {
            return;
        }
        // 选取右边第一个作为参考值
        int data = array[r];
        int start = r;
        int end = l;
        while (start != end) {
            // 从左边开始找比中间值小的
            while (array[end] >= data && start < end) {
                end--;
            }
            while (array[start] <= data && start < end) {
                start++;
            }
            // 交换这两个数
            if (start < end) {
                int a = array[start];
                array[start] = array[end];
                array[end] = a;
            }

        }
        //当退出上面的循环，说明 i 和 j 的位置交汇了，更换参考值与 i 位置的值。
        array[r] = array[start];
        array[start] = data;

        quickSort(array, r, start - 1);
        quickSort(array, start + 1, l);
    }

    private void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}