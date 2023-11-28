package tech.alexchen.daydayup.algorithm.leetcode;

import cn.hutool.core.util.StrUtil;
import tech.alexchen.daydayup.algorithm.AlgoUtil;

import java.util.*;

/**
 * @author alexchen
 */
public class T210 {

    public static void main(String[] args) {
        T210 t = new T210();
        int numCourses = 4;
        int[][] prerequisites = new int[][]{
                {1,0},{2,0},{3,1},{3,2}
        };
        int[] order = t.findOrder(4, prerequisites);
        System.out.println(Arrays.toString(order));
    }

    /**
     * 记录后序遍历结果
     */
    List<Integer> postorder = new ArrayList<>();
    boolean[] visited;
    boolean[] onPath;
    boolean hasCycle = false;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. 构建图
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        //
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        // 2. 遍历
        for (int i = 0; i < numCourses; i++) {
            dfs(graph, i);
        }
        if (hasCycle) {
            return new int[0];
        }
        // 逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 每个结点，都存储一个学习其之前需要学习的课程列表
        List<Integer>[] graph = new LinkedList[numCourses];
        // 初始化
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            // 修完课程 from 才能修课程 to
            // 在图中添加一条从 from 指向 to 的有向边
            graph[from].add(to);
        }
        return graph;
    }

    void dfs(List<Integer>[] graph, int cur) {
        AlgoUtil.printIndentBefore();
        System.out.println(StrUtil.format("cur: {}, visited: {}, onPath: {}, graph[]: {}",
                cur, visited[cur], onPath[cur], graph[cur]));
        // 判断是否有环
        if (onPath[cur]) {
            hasCycle = true;
        }
        // 如果已经访问过来了，或者存在环
        if (visited[cur] || hasCycle) {
            AlgoUtil.after();
            return;
        }
        visited[cur] = true;
        onPath[cur] = true;
        // 遍历相邻结点
        for (int beforeCourse : graph[cur]) {
            dfs(graph, beforeCourse);
        }
        // 后序遍历位置
        postorder.add(cur);
        // 回溯
        onPath[cur] = false;
        AlgoUtil.after();
    }
}
