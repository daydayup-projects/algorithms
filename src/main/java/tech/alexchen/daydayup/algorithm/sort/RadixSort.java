package tech.alexchen.daydayup.algorithm.sort;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;

/**
 * @author alexchen
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] ints = RandomUtil.randomInts(16);
        System.out.println("Before: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("After:  " + Arrays.toString(ints));
    }

    public static void sort(int[] a) {

    }
}
