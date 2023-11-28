package tech.alexchen.daydayup.ds.graph;

import java.util.Arrays;

/**
 * 邻接表实现有向图
 *
 * @author AlexChen
 * @date 2022-07-12 12:40
 */
public class ListDirectedGraph {

    private final VertexNode[] vertexNodes;
    private final int vertexNum;

    /**
     * 边节点
     */
    private class EdgeNode {
        int index;
        EdgeNode nextNode;

        public EdgeNode() {
        }

        public EdgeNode(int index) {
            this.index = index;
        }

        public EdgeNode(int index, EdgeNode nextNode) {
            this.index = index;
            this.nextNode = nextNode;
        }
    }

    /**
     * 顶点节点
     */
    private class VertexNode {
        char data;
        EdgeNode node;

        public VertexNode() {

        }

        public VertexNode(char data) {
            this.data = data;
        }

        public VertexNode(char data, EdgeNode node) {
            this.data = data;
            this.node = node;
        }
    }

    /**
     * 图初始化
     */
    public ListDirectedGraph(char[] vertexes, char[][] edges) {
        vertexNum = vertexes.length;
        vertexNodes = new VertexNode[vertexNum];
        // 初始化节点
        for (int i = 0; i < vertexNum; i++) {
            vertexNodes[i] = new VertexNode(vertexes[i]);
        }

        // 初始化边
        for (int i = 0; i < edges.length; i++) {
            int start = getPosition(edges[i][0]);
            int end = getPosition(edges[i][1]);

            EdgeNode node = new EdgeNode(end);
            if (vertexNodes[start].node == null) {
                vertexNodes[start].node = node;
            } else {
                linkLast(vertexNodes[start].node, node);
            }
            // 由于有向图的边是有方向的，不需要再构建从 end 到 start 的边
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexNum; i++) {
            if (vertexNodes[i] != null && vertexNodes[i].data == ch) {
                return i;
            }
        }
        return -1;
    }

    private void linkLast(EdgeNode src, EdgeNode node) {
        EdgeNode last = src;
        while (last.nextNode != null) {
            last = last.nextNode;
        }
        last.nextNode = node;
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

    private void DFS(int i, boolean[] visited) {
        visited[i] = true;
        System.out.printf("%c ", vertexNodes[i].data);

        EdgeNode node = vertexNodes[i].node;
        while (node != null) {
            if (!visited[node.index]) {
                DFS(node.index, visited);
            }
            node = node.nextNode;
        }
    }

    public void BFS() {
        int head = 0;
        int rear = 0;
        int[] queue = new int[vertexNum];
        boolean[] visited = new boolean[vertexNum];
        Arrays.fill(visited, false);

        System.out.printf("BFS: ");
        for (int i = 0; i < vertexNum; i++) {
            // 访问当前顶点
            if (!visited[i]) {
                System.out.printf("%c ", vertexNodes[i].data);
                visited[i] = true;
                // 入队列
                queue[rear++] = i;
            }
            // 队列不为空时，依次对队列每个元素进行BFS
            while (head != rear) {
                int curr = queue[head++];
                EdgeNode node = vertexNodes[curr].node;
                while (node != null) {
                    int index = node.index;
                    if (!visited[index]) {
                        visited[index] = true;
                        System.out.printf("%c ", vertexNodes[index].data);
                        queue[rear++] = index;
                    }
                    node = node.nextNode;
                }
            }
        }
        System.out.println();
    }

    private void print() {
        System.out.printf("List Graph:\n");
        for (int i = 0; i < vertexNum; i++) {
            System.out.printf("%d(%c): ", i, vertexNodes[i].data);
            EdgeNode node = vertexNodes[i].node;
            while (node != null) {
                System.out.printf("%d(%c) ", node.index, vertexNodes[node.index].data);
                node = node.nextNode;
            }
            System.out.printf("\n");
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
        ListDirectedGraph listDirectedGraph = new ListDirectedGraph(vertexes, edges);
        listDirectedGraph.print();
        listDirectedGraph.DFS();
        listDirectedGraph.BFS();
    }
}
