package baekjoon;

import java.io.*;
import java.util.*;

public class SNS_2533 {

    private static int[][] dp;
    private static List<Integer>[] graph; // 트리를 만들기 위한 인접 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // dp[i][j] = i번 노드가 root인 서브 트리에서 필요한 최소 얼리어답터 수
        // j = 0: i번 노드가 얼리어답터가 아닐 경우, j = 1: i번 노드가 얼리어답터일 경우
        dp = new int[n + 1][2];
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            // 리프노드에 사용될 초기값
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        compute(1, -1);

        int answer = Math.min(dp[1][0], dp[1][1]); // 최소 얼리어답터 수
        System.out.println(answer);
    }

    // bottom up - 리프노드부터 올라가며 계산
    private static void compute(int current, int parent) {
        int min = 0;
        int sum = 0;
        for (int next : graph[current]) {
            if (next != parent) {
                compute(next, current);
                min += Math.min(dp[next][0], dp[next][1]);
                sum += dp[next][1];
            }
        }

        // 리프노드가 아닐 경우
        if (graph[current].size() != 0) {
            dp[current][0] = sum; // 자신이 얼리어답터가 아니면 자식들이 모두 얼리어답터여야 함
            dp[current][1] = min + 1; // 자신이 얼리어답터면 자식들은 얼리어답터가 아니어도 됨 -> 최소값으로 계산
        }
    }
}
