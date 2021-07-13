package baekjoon;

import java.io.*;
import java.util.*;

// MST(Minimum Spanning Tree)
// Kruskal Algorithm
public class ConnectNetwork_1922 {

    private static Edge[] edges;
    private static int[] parent;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st;

        // 노드 - 1번 ~ n번 이므로 n+1
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }

        // cost가 작은 순으로 정렬
        Arrays.sort(edges);

        for (Edge edge: edges) {
            // 연결되어 있지 않을 경우(같은 집합에 속해 있으면 cycle 형성됨)
            if (find(edge.node1) != find(edge.node2)) {
                union(edge.node1, edge.node2);
                answer += edge.cost;
            }
        }
        System.out.println(answer);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parent[y] = x;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Edge implements Comparable<Edge> {

        private int node1, node2, cost;

        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
}
