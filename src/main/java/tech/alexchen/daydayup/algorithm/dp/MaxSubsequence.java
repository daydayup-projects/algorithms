package tech.alexchen.daydayup.algorithm.dp;

/**
 * 最大子序列
 *
 * @author AlexChen
 * @date 2022-06-16 04:44
 */
public class MaxSubsequence {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println(forceSolution(arr));
        System.out.println(dynamicSolution(arr));
        System.out.println(greedySolution(arr));
    }

    /**
     * 暴力法
     * O(N^2)
     *
     * @param nums
     * @return
     */
    public static int forceSolution(int[] nums) {
        int n = nums.length;
        int maxSum = 0;
        boolean hasReplaced = false;
        // left 表示子序列的左端位置
        for (int left = 0; left < n; left++) {
            // right 表示子序列的右端位置，从 left 开始
            int sum = 0;
            for (int right = left; right < n; right++) {
                // 滑动子序列，对子序列求和
                sum += nums[right];
                if (!hasReplaced) {
                    maxSum = sum;
                    hasReplaced = true;
                } else if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 动态规划
     * 如果前边累加后还不如自己本身大，那就把前边的都扔掉，从此自己本身重新开始累加
     * O(N)
     *
     * @param nums
     * @return
     */
    public static int dynamicSolution(int[] nums) {
        int curr = 0;
        int maxAns = nums[0];
        for (int num : nums) {
            //
            curr = Math.max(num, curr + num);
            maxAns = Math.max(maxAns, curr);
        }
        return maxAns;
    }

    /**
     * 贪心算法
     * O(N)
     */
    public static int greedySolution(int[] nums) {
        //类似寻找最大最小值的题目,初始值一定要定义成理论上的最小最大值
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result = Math.max(result, sum);
            //如果sum < 0,重新开始找子序串
            if (sum < 0) {
                sum = 0;
            }
        }
        return result;
    }

    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    /**
     * 分治算法
     * O（NlogN）
     */
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

}
