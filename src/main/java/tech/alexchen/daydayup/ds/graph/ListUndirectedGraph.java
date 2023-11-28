package tech.alexchen.daydayup.ds.graph;

import java.util.Arrays;

/**
 * 邻接表实现无向图
 *
 * @author AlexChen
 * @date 2022-07-12 12:40
 */
public class ListUndirectedGraph {

    private final VertexNode[] vertexNodes;
    private final int vertexNum;

    private class EdgeNode {
        int index;
        EdgeNode nextNode;
    }

    private class VertexNode {
        char data;
        EdgeNode node;
    }

    public ListUndirectedGraph(char[] inputVertexes, char[][] inputEdges) {
        vertexNum = inputVertexes.length;
        //初始化顶点
        vertexNodes = new VertexNode[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            vertexNodes[i] = new VertexNode();
            vertexNodes[i].data = inputVertexes[i];
            vertexNodes[i].node = null;
        }
        //初始化边
        for (int i = 0; i < inputEdges.length; i++) {
            // 读取边的起始顶点和结束顶点，将结束顶点对象挂在起始顶点的
            int start = getPosition(inputEdges[i][0]);
            int end = getPosition(inputEdges[i][1]);
            // 将结束顶点链接到起始顶点所在链表的末尾
            EdgeNode endNode = new EdgeNode();
            endNode.index = end;
            if (vertexNodes[start].node == null) {
                vertexNodes[start].node = endNode;
            } else {
                linkLast(vertexNodes[start].node, endNode);
            }
            // 将起始顶点链接到结束顶点所在链表的末尾
            EdgeNode startNode = new EdgeNode();
            startNode.index = start;
            if (vertexNodes[end].node == null) {
                vertexNodes[end].node = startNode;
            } else {
                linkLast(vertexNodes[end].node, startNode);
            }
        }

    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexNum; i++) {
            if (vertexNodes[i].data == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 将节点 node 链接到 list 的最后
     */
    private void linkLast(EdgeNode list, EdgeNode node) {
        EdgeNode temp = list;
        while (temp.nextNode != null) {
            temp = temp.nextNode;
        }
        temp.nextNode = node;
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
        // 访问当前节点
        visited[i] = true;
        System.out.printf("%c ", vertexNodes[i].data);
        // 获取链表第一条边，从其开始递归调用DFS
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
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", vertexNodes[i].data);
                queue[rear++] = i;
            }
            // 队列不为空时
            while (head != rear) {
                int index = queue[head++];
                //遍历当前顶点的邻接顶点，然后入队列
                EdgeNode node = vertexNodes[index].node;
                while (node != null) {
                    if (!visited[node.index]) {
                        visited[node.index] = true;
                        System.out.printf("%c ", vertexNodes[node.index].data);
                        queue[rear++] = node.index;
                    }
                    node = node.nextNode;
                }
            }
        }
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
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};
        ListUndirectedGraph listUndirectedGraph = new ListUndirectedGraph(vertexes, edges);
        listUndirectedGraph.print();
        listUndirectedGraph.DFS();
        listUndirectedGraph.BFS();
    }
}
