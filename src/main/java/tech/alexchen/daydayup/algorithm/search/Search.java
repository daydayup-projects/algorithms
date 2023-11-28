package tech.alexchen.daydayup.algorithm.search;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

/**
 * 查找算法：
 * 1. 顺序查找
 * 2. 二分查找/折半查找
 * 3. 插值查找
 * 4. 斐波那契查找
 *
 * @author alexchen
 * @date 2022/6/30
 */
public class Search {

    public static void main(String[] args) {
        int[] randomArray = {0, 1, 6, 3, 9, 4, 7, 9, 0, 2, 34, 67, 12};
        int[] sortArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int[] sortArray2 = {1, 34, 636, 2456, 6541, 72567};

//        System.out.println(orderSearch(randomArray, 34));
//
//        System.out.println(binarySearch(sortArray, 10));
//
//        System.out.println(interpolationSearch(sortArray, 10));

        System.out.println(StrUtil.format("斐波那契查找数组的结果为：{}", fibSearch(sortArray, 11)));
    }

    /**
     * 顺序查找算法：<br>
     * 对于任意一个序列以及一个给定的元素，将给定元素与序列中元素依次比较，直到找出与给定关键字相同的元素，或者将序列中的元素与其都比较完为止。
     * 时间复杂度 O(N)
     *
     * @param arr 数组
     * @param key 要查找的元素
     * @return 查找到的元素下标，未查找到返回-1
     */
    public static int orderSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (key == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分法查找（折半查找）：<br>
     * 搜索过程从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜索过程结束；
     * 如果某一特定元素大于或者小于中间元素，则在数组大于或小于中间元素的那一半中查找，而且跟开始一样从中间元素开始比较。
     * 如果在某一步骤数组为空，则代表找不到。这种搜索算法每一次比较都使搜索范围缩小一半。
     * 时间复杂度 O(logN)
     *
     * @param arr 从小到达排好序的数组
     * @param key 要查找的元素
     * @return 查找到的元素下标，未查找到返回-1
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
//            int mid = (low + high) >>> 1;
            // 目标值小于中间值，说明结果在左侧较小的那部分，改变 high 指针；反之在较大部分，改变 low 指针
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                //找到值
                return mid;
            }
        }
        // 未找到
        return -1;
    }

    /**
     * 插值查找：<br>
     * 插值查找算法类似于二分查找，不同的是插值查找每次从自适应mid处开始查找;
     * 二分查找中，mid = (low + high)/2 = low + 1/2(high - low)；
     * 在插值查找中，mid = low + {(key - arr[low])/(arr[high]-arr[low])}*(high - low)
     * 时间复杂度 O(log2(log2N))
     *
     * @param arr
     * @param key
     * @return
     */
    public static int interpolationSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (key - arr[low]) / (arr[high] - arr[low]) * (high - low);
            if (key < arr[mid]) {
                high = mid - 1;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else {
                //找到值
                return mid;
            }
        }
        return -1;
    }

    /**
     * 斐波那契查找：<br>
     * 斐波那契查找是二分法的一种提升算法，主要不同在于中间值的确定，通过运用黄金比例的概念在数列中选择查找点进行查找，提高查找效率；
     * 他要求开始表中记录的个数为某个斐波那契数小1，及n=F(k)-1；开始将k值与第F(k-1)位置的记录进行比较(及mid=low+F(k-1)-1)，比较结果也分为三种：
     * （1）相等，则mid位置的元素即为所求；
     * （2）>，则low=mid+1，k-=2；
     * 说明：low=mid+1说明待查找的元素在[mid+1,high]范围内，k-=2 说明范围[mid+1,high]内的元素个数为n-(F(k-1))=Fk-1-F(k-1)=Fk-F(k-1)-1=F(k-2)-1个，所以可以递归的应用斐波那契查找。
     * （3））<，则high=mid-1，k-=1。
     * 说明：low=mid+1说明待查找的元素在[low,mid-1]范围内，k-=1 说明范围[low,mid-1]内的元素个数为F(k-1)-1个，所以可以递归的应用斐波那契查找。
     * 在最坏情况下，斐波那契查找的时间复杂度还是O(log2N)，且其期望复杂度也为O(log2N)，但是与折半查找相比，斐波那契查找的优点是它只涉及加法和减法运算，
     * 而不用除法，而除法比加减法要占用更多的时间，因此，斐波那契查找的运行时间理论上比折半查找小，但是还是得视具体情况而定。
     *
     * @param arr
     * @param key
     * @return
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        // 生成斐波那契数列
        int[] f = fib(20);
        // 判断顺序表的长度 n 是否等于 f(k)-1，不等于则将原数组扩展为长度为 f(k)-1
        // 示例：1, 34, 636, 2456, 6541, 72567   high=5
        // 1 1 2 3 5 8        k=5 f[5]=8
        while (high > f[k] - 1) {
            k++;
        }
        // 扩容数组，使用最后一个元素填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            int mid = low + f[k - 1] - 1;// f[k] = f[k-1] + f[k-2]，左侧长度为 f[k-1]，后面再减一得到数组下标



            if (key < arr[mid]) {
                System.out.println(StrUtil.format("值在左边,中间下标为：{}, 中间值为：{}", mid, temp[mid]));
                System.out.printf("数组左边为：");
                for (int i = low; i < mid; i++) {
                    System.out.printf(temp[i] + " ");
                }
                System.out.println();

                high = mid - 1;
                // f[k] -1 = f[k-1] + f[k-2] -1 = (f[k-1] -1) + (f[k-2] -1) + 1
                // 左边部分为 f[k-1] -1
                // 而 f[k-1] -1 = (f[k-1-1] -1) + (f[k-2-2] -1) + 1
                // 再取左边 k 减 1，取右边 k 减 2
                k--;

                System.out.println(StrUtil.format("k = {}, f[k] = {}, f[k-1] -1 = {} ", k, f[k], f[k-1] -1));
            } else if (key > arr[mid]) {
                System.out.println(StrUtil.format("值在右边,中间下标为：{}, 中间值为：{}", mid, temp[mid]));
                System.out.printf("数组右边为：");
                for (int i = mid + 1; i <= high; i++) {
                    System.out.printf(temp[i] + " ");
                }
                System.out.println();

                low = mid + 1;
                k -= 2;
                System.out.println(StrUtil.format("k = {}, f[k] = {}, f[k-1] -1 = {} ", k, f[k], f[k-1] -1));
            } else {
                //找到值
                return mid;
            }
            System.out.println();
        }
        return -1;
    }

    public static int[] fib(int maxSize) {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; ++i) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
