package tech.alexchen.daydayup.ds.string;

import java.util.Arrays;

/**
 * @author alexchen
 */
public class KMP {

    private int[][] dp;
    private String pattern;

    public KMP(String pattern) {
        this.pattern = pattern;
        int M = pattern.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        dp[0][pattern.charAt(0)] = 1;
        // 影子状态 X 初始为 0
        int X = 0;
        // 当前状态 j 从 1 开始
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                if (pattern.charAt(j) == c) {
                    dp[j][c] = j + 1;
                } else {
                    dp[j][c] = dp[X][c];
                }
            }
            // 更新影子状态
            X = dp[X][pattern.charAt(j)];
        }
    }

    public int search(String text) {
        int m = pattern.length();
        int n = text.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            // 当前是状态 j，遇到字符 text[i]，pattern 应该转移到哪个状态？
            j = dp[j][text.charAt(i)];
            // 如果达到终止态，返回匹配开头的索引
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    public void print() {
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }

    }

    public static void main(String[] args) {
        KMP kmp = new KMP("aaab");
        kmp.print();
        System.out.println(kmp.search("aaacaaab"));
    }

}
