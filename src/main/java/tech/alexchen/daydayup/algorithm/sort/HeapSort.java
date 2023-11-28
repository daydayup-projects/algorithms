package tech.alexchen.daydayup.algorithm.sort;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author alexchen
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] ints = RandomUtil.randomInts(16);
        System.out.println("Before: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After:  " + Arrays.toString(ints));
    }

    public static void sort(int[] a) {
        int n = a.length;
        // 初始化堆
        buildMaxHeap(a, n);
        //
        for (int i = n - 1; i > 0; i--) {
            // 交换堆顶 a[0] 和堆底 a[i] 的值
            swap(a, 0, i);
            maxHeapify(a, 0, i);
        }
    }

    private static void buildMaxHeap(int[] a, int n) {
        // i 初始化为最后一个结点的父结点在数组的下标
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(a, i, n);
        }
    }

    private static void maxHeapify(int[] a, int i, int n) {
        int left = 2 * i + 1;
        int right = left + 1;
        int largest = i;
        if (left < n && a[left] > a[largest]) {
            largest = left;
        }
        if (right < n && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, n);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
