package baekjoon;

import java.io.*;
import java.util.*;

public class LCA_11437 {

    private static List<Integer>[] graph; // 인접 리스트
    private static int[] depths; // 각 노드의 깊이
    private static int[] parents; // 각 노드의 부모 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        // 초기화
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        depths = new int[n + 1];
        parents = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0, -1);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(lca(a, b));
        }
    }

    // 각 노드의 깊이 구하기
    public static void dfs(int current, int depth, int parent) {
        depths[current] = depth;
        for (int node: graph[current]) {
            if (node != parent) {
                parents[node] = current;
                dfs(node, depth + 1, current);
            }
        }
    }

    // 최소 공통 조상 반환
    public static int lca(int node1, int node2) {
        // 깊이가 다르면 깊이를 맞춰줌
        while (depths[node1] != depths[node2]) {
            if (depths[node1] > depths[node2]) {
                node1 = parents[node1];
            } else {
                node2 = parents[node2];
            }
        }
        // 같아질 때까지 반복
        while (node1 != node2) {
            node1 = parents[node1];
            node2 = parents[node2];
        }
        return node1;
    }
}
