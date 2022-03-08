package baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FriendCost_16562 {

    private static int[] parents;
    private static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parents = IntStream.range(0, n + 1).toArray();
        costs = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);
        }

        // 루트 노드를 저장
        Set<Integer> parentSet = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            parentSet.add(find(parents[i]));
        }

        // 모든 루트 노드와 연결
        int totalCost = 0;
        for (Integer parent : parentSet) {
            totalCost += costs[parent];
            if (totalCost > k) {
                System.out.println("Oh no");
                return;
            }
        }
        System.out.println(totalCost);
    }

    private static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);
        if (node1 == node2) {
            return;
        }
        parents[node2] = node1;
        // 두 비용 중 적은 비용을 저장
        int minCost = Math.min(costs[node1], costs[node2]);
        costs[node1] = minCost;
        costs[node2] = minCost;
    }

    private static int find(int node) {
        if (node == parents[node]) {
            return node;
        }
        return parents[node] = find(parents[node]);
    }
}
