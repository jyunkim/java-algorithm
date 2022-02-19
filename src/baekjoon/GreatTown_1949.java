package baekjoon;

import java.io.*;
import java.util.*;

public class GreatTown_1949 {

    private static List<Integer>[] tree;
    private static int[] populations;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new List[n + 1];
        populations = new int[n + 1];
        dp = new int[n + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
            populations[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        // dp[i][j]: i번째 마을이 루트 노드인 트리에서 우수 마을로 선정된 마을 주민 수 합의 최댓값
        // j = 0 -> i번째 마을이 우수 마을이 아닐 경우, j = 1 -> i번째 마을이 우수 마을인 경우
        int root = 1;
        dfs(root, -1);

        System.out.println(Math.max(dp[root][0], dp[root][1]));
    }

    private static void dfs(int current, int parent) {
        dp[current][1] = populations[current];

        for (int next : tree[current]) {
            if (next != parent) { // 리프 노드까지 dfs로 들어간 후 dp 시작(방문한 노드는 다시 방문하지 않음)
                dfs(next, current);
                // 부모 노드가 우수 마을이 아니면 자식 노드 중 하나는 우수 마을이어야 함
                // 만약 자식 노드가 모두 우수 마을이 아닌 경우가 최댓값이 되면 어떡하나?
                // -> 그런 경우에는 dp[current][1]이 dp[current][0] 보다 무조건 크기 때문에(populations[current] 만큼) 상관 없음
                dp[current][0] += Math.max(dp[next][0], dp[next][1]);
                // 부모 노드가 우수 마을이면 모든 자식 노드는 우수 마을이 될 수 없음
                dp[current][1] += dp[next][0];
            }
        }
    }
}
