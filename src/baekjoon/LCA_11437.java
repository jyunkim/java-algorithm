package baekjoon;

import java.io.*;
import java.util.*;

public class LCA_11437 {

    private static ArrayList<Integer>[] graph; // 인접 리스트
    private static int[] depth; // 각 노드의 깊이
    private static boolean[] visited; // 방문 여부
    private static int[] parent; // 각 노드의 부모 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        // 초기화
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        depth = new int[n + 1];
        visited = new boolean[n + 1];
        parent = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(lca(a, b));
        }
    }

    // 각 노드의 깊이 구하기
    public static void dfs(int cur, int cur_depth) {
        visited[cur] = true;
        depth[cur] = cur_depth;
        for (int node: graph[cur]) {
            if (!visited[node]) {
                parent[node] = cur;
                dfs(node, cur_depth + 1);
            }
        }
    }

    // 최소 공통 조상 반환
    public static int lca(int node1, int node2) {
        // 깊이가 다르면 깊이를 맞춰줌
        while (depth[node1] != depth[node2]) {
            if (depth[node1] > depth[node2]) {
                node1 = parent[node1];
            } else {
                node2 = parent[node2];
            }
        }
        // 같아질 때까지 반복
        while (node1 != node2) {
            node1 = parent[node1];
            node2 = parent[node2];
        }
        return node1;
    }
}
