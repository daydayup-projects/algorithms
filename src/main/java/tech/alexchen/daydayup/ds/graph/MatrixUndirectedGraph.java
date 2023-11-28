package tech.alexchen.daydayup.ds.graph;

import java.util.Arrays;

/**
 * 邻接矩阵实现无向图
 *
 * @author AlexChen
 * @date 2022-07-12 12:37
 */
public class MatrixUndirectedGraph {

    /**
     * 邻接矩阵
     */
    private final int[][] matrix;

    /**
     * 顶点集合
     */
    private final char[] vertexes;

    /**
     * 顶点数
     */
    private final int vertexNum;

    /**
     * 边数
     */
    private final int edgeNum;

    /**
     * 通过指定顶点集合以及边之间的连接关系构建邻接矩阵无向图
     *
     * @param vertexes 顶点集合
     * @param edges    边之间的连接关系
     */
    public MatrixUndirectedGraph(char[] vertexes, char[][] edges) {
        if (vertexes == null || vertexes.length == 0 || edges == null) {
            throw new IllegalArgumentException();
        }
        vertexNum = vertexes.length;
        edgeNum = edges.length;

        //初始化顶点集合
        this.vertexes = Arrays.copyOf(vertexes, vertexNum);
        //初始化边
        this.matrix = new int[vertexNum][vertexNum];
        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);

            matrix[p1][p2] = 1;
            matrix[p2][p1] = 1;
        }
    }

    /**
     * 读取顶点在数组中的下标
     */
    private int getPosition(char object) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == object) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先搜索
     */
    public void DFS() {
        boolean[] visited = new boolean[vertexNum];
        Arrays.fill(visited, false);

        System.out.printf("DFS: ");
        for (int i = 0; i < vertexNum; i++) {
            //遍历每个顶点，如果该顶点未被访问，则从其开始深度优先遍历
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
        System.out.println();
    }

    private void DFS(int v, boolean[] visited) {
        //访问当前顶点
        System.out.printf("%c ", vertexes[v]);
        //修改访问状态
        visited[v] = true;
        //
        for (int w = firstVertex(v); w >= 0; w = nextVertex(v, w)) {
            if (!visited[w]) {
                DFS(w, visited);
            }
        }
    }

    private int firstVertex(int v) {
        return nextVertex(v, -1);
    }

    /**
     * 获取顶点 V 相对于顶点 W 的下一个邻接顶点的索引，失败则返回 -1
     *
     * @param v 顶点 v 的索引
     * @param w 顶点 w 的索引；特殊地，当 w 为 -1 时，表示从头开始取第一个邻接顶点的索引
     * @return 下一个邻接顶点的索引，失败返回时 -1
     */
    private int nextVertex(int v, int w) {
        if (v < 0 || v >= vertexNum || w < -1 || w >= vertexNum) {
            return -1;
        }

        for (int i = w + 1; i < vertexNum; i++) {
            if (matrix[v][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 广度优先搜索（类似于树的层次遍历）
     * 步骤：
     * 1. 访问第一个顶点，将该顶点入队列
     * 2. 顶点出队，访问该顶点的邻接顶点
     */
    public void BFS() {
        //指针
        int head = 0;
        int rear = 0;
        //辅助队列
        int[] queue = new int[vertexNum];
        //访问状态标记数组
        boolean[] visited = new boolean[vertexNum];
        //初始化为都未访问
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        //开始广度优先搜索
        System.out.printf("BFS: ");
        //从初始顶点开始，依次访问未访问的顶点（防止有顶点没有边，从一个顶点搜索一遍后，没有全部遍历）
        for (int i = 0; i < vertexNum; i++) {
            //访问当前循环的顶点，进行BFS
            if (!visited[i]) {
                //访问该顶点
                System.out.printf("%c ", vertexes[i]);
                visited[i] = true;
                //当前顶点入队列
                queue[rear++] = i;
            }
            //队列不为空
            while (head != rear) {
                //出队列
                int curr = queue[head++];
                for (int j = firstVertex(curr); j >=0 ; j = nextVertex(curr, j)) {
                    if (!visited[j]) {
                        visited[j] = true;
                        System.out.printf("%c ", vertexes[j]);
                        queue[rear++] = j;
                    }
                }
            }
        }
    }

    public void print() {
        System.out.println("Matrix Graph:");
        System.out.printf("  ");
        for (char vertex : vertexes) {
            System.out.printf("%c ", vertex);
        }
        System.out.println();
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                if (j == 0) {
                    System.out.printf("%c ", vertexes[i]);
                }
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};
        MatrixUndirectedGraph matrixUndirectedGraph = new MatrixUndirectedGraph(vertexes, edges);

        matrixUndirectedGraph.print();
        matrixUndirectedGraph.DFS();
        matrixUndirectedGraph.BFS();
    }
}
