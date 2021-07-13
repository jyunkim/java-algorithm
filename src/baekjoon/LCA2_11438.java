package baekjoon;

import java.io.*;
import java.util.*;

/**
 * N = 노드 수, M = 구해야 되는 LCA 수
 * LCA1 방식
 * 시간복잡도 = O(MN)
 * N, M = 100,000
 * MN = 100,000,000,000 -> 시간 초과
 */

// 거슬러 올라가는 속도를 빠르게 - 2^n칸씩 O(logN)
// DP
public class LCA2_11438 {

    private static ArrayList<Integer>[] graph; // 인접 리스트
    private static int[] depth; // 각 노드의 깊이
    private static boolean[] visited; // 방문 여부
    private static int[][] parents; // 각 노드의 부모 노드
    private static int maxI; // 깊이 최대 거듭제곱 지수(2^i)

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

        int temp = 1;
        while (temp <= n) {
            temp *= 2;
            maxI++;
        }
        parents = new int[n + 1][maxI]; // 노드별로 2^i 부모 노드 저장

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0);
        setParent(n);

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
                parents[node][0] = cur;
                dfs(node, cur_depth + 1);
            }
        }
    }

    // 2^i번째 부모까지 저장
    public static void setParent(int n) {
        for (int i = 1; i < maxI; i++) {
            for (int j = 1; j <= n; j++) {
                // j번째 노드의 2^i번째 부모는
                // j번째 노드의 2^(i-1)번째 부모의 2^(i-1)번째 부모
                parents[j][i] = parents[parents[j][i - 1]][i - 1];
            }
        }
    }

    // 최소 공통 조상 반환
    // 2^i씩 이동
    public static int lca(int node1, int node2) {
        // node1을 더 깊은 노드로 설정
        if (depth[node1] < depth[node2]) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }
        // 더 깊은 노드를 올려 동일한 깊이로 맞춤
        // i를 점차 줄여가며 깊이 차이보다 작아지면 이동
        for (int i = maxI - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[node1] - depth[node2]) {
                node1 = parents[node1][i];
            }3
        }
        // 만약 같아졌으면 반환
        if (node1 == node2) return node1;

        // 거듭제곱으로 갈 수 있는 최소 공통 부모 이전까지 이동 반복
        for (int i = maxI - 1; i >= 0; i--) {
            if (parents[node1][i] != parents[node2][i]) {
                node1 = parents[node1][i];
                node2 = parents[node2][i];
            }
        }
        // 바로 위 부모 반환
        return parents[node1][0];
    }
}
