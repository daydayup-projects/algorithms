package tech.alexchen.daydayup.ds.array;

import java.util.Arrays;

/**
 * leetcode 645
 * <p>
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * <p>
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 * 示例 2：
 * <p>
 * 输入：nums = [1,1]
 * 输出：[1,2]
 *
 * @author AlexChen
 * @date 2022-05-29 15:48
 */
public class FindErrorNums {

    public static void main(String[] args) {
        int[] array = new int[]{3, 2, 3, 4, 6, 5};
//        int[] array = new int[]{1,3,2,4,6,5,7,7};
        Arrays.sort(array);
        for (int num : array) {
            System.out.printf(num + "");
        }
        System.out.println();

        int[] errorNums = findErrorNums(array);
        for (int i = 0; i < errorNums.length; i++) {
            System.out.printf(String.valueOf(errorNums[i]));
        }
        System.out.println();
    }

    public static int[] findErrorNums(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            // nums[i] != i + 1 说明位置错乱或者数字变更了
//            // nums[i] != nums[nums[i] - 1]  说明位置错乱而不是变更了
//            // i = 0, nums[0] 应该等于1，如果替换为 n， 那么 nums[n -1] 应该和nums[0]一样为 n
//            // i = 4, nums[4] 为6 而不是5，再判断 nums[5] 为 5 而不是6，说明位置错乱，替换位置
//            while (nums[i] != i + 1 &&
//                    nums[i] != nums[nums[i] - 1]) {
//                swap(nums, i, nums[i] - 1);
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != i + 1) {
//                return new int[]{nums[i], i + 1};
//            }
//        }
//        return null;

        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
