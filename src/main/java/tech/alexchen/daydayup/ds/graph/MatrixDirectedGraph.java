package tech.alexchen.daydayup.ds.graph;

import java.util.Arrays;

/**
 * 邻接矩阵实现有向图
 *
 * @author AlexChen
 * @date 2022-07-12 12:38
 */
public class MatrixDirectedGraph {

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


    public MatrixDirectedGraph(char[] vertexes, char[][] edges) {
        vertexNum = vertexes.length;
        edgeNum = edges.length;

        this.vertexes = Arrays.copyOf(vertexes, vertexNum);
        this.matrix = new int[vertexNum][vertexNum];
        for (int i = 0; i < edgeNum; i++) {
            // 读取边的起始顶点和结束顶点
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);

            // 有向图和无向图的区别点
            matrix[p1][p2] = 1;
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    private int firstVertex(int v) {
        return nextVertex(v, -1);
    }

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

    public void DFS() {
        boolean[] visited = new boolean[vertexNum];
        Arrays.fill(visited, false);

        System.out.printf("DFS: ");
        for (int i = 0; i < vertexNum; i++) {
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
        System.out.println();
    }

    private void DFS(int v, boolean[] visited) {
        System.out.printf("%c ", vertexes[v]);
        visited[v] = true;

        for (int w = firstVertex(v); w >= 0;) {
            //进行 DFS 前的判断很重要，不加的话会陷入死循环
            if (!visited[w]) {
                DFS(w, visited);
            }
            // 当前邻接点搜索后，指针切换到下一个邻接点
            w = nextVertex(v, w);
        }
    }

    public void BFS() {
        int head = 0;
        int rear = 0;
        boolean[] visited = new boolean[vertexNum];
        Arrays.fill(visited, false);
        int[] queue = new int[vertexNum];

        System.out.printf("BFS: ");
        for (int i = 0; i < vertexNum; i++) {
            if (!visited[i]) {
                System.out.printf("%c ", vertexes[i]);
                visited[i] = true;
                queue[rear++] = i;
            }

            while (head != rear) {
                int v = queue[head++];
                for (int w = firstVertex(v); w >= 0 ; w = nextVertex(v, w)) {
                    if (!visited[w]) {
                        System.out.printf("%c ", vertexes[w]);
                        visited[w] = true;
                        queue[rear++] = w;
                    }
                }

            }
        }
        System.out.println();
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
                {'A', 'B'},
                {'B', 'C'},
                {'B', 'E'},
                {'B', 'F'},
                {'C', 'E'},
                {'D', 'C'},
                {'E', 'B'},
                {'E', 'D'},
                {'F', 'G'}};
        MatrixDirectedGraph matrixDirectedGraph = new MatrixDirectedGraph(vertexes, edges);

        matrixDirectedGraph.print();
        matrixDirectedGraph.DFS();
        matrixDirectedGraph.BFS();
    }

}
