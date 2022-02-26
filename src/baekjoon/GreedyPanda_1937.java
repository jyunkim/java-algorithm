package baekjoon;

import java.util.*;
import java.io.*;

// dfs - 시간 초과
// 출발점이 바뀔 때마다 새로 계산하지 않고 값을 저장한 후 다음에 사용(dp)
public class GreedyPanda_1937 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int[][] forest;
    private static int[][] dp;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        forest = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    // dp[i][j] = i, j에서 이동할 수 있는 칸 수의 최댓값
    private static int dfs(int x, int y) {
        // 이전에 방문한 곳이면 저장된 값을 반환 - 이 값은 바뀌지 않음
        if (dp[x][y] > 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }

            if (forest[x][y] < forest[nx][ny]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1); // 네 방향 중 최댓값을 저장
            }
        }
        return dp[x][y];
    }
}
