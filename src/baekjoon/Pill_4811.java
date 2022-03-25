package baekjoon;

import java.io.*;

public class Pill_4811 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 이전의 선택들이 영향을 미침, 부분 문제 -> dp
        // dp[i][j] = 한 조각을 i번, 반 조각을 j번 꺼냈을 때 가능한 경우의 수
        // j가 i보다 클 수는 없음
        long[][] dp = new long[31][31];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    // 한 조각을 i번, 반 조각을 j번 꺼내려면
                    // 한 조각을 i-1번, 반 조각을 j번 꺼내고 한 조각을 꺼내거나 한 조각을 i번, 반 조각을 j-1번 꺼내고 반 조각을 꺼내야 함
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            System.out.println(dp[n][n]);
        }
    }
}
