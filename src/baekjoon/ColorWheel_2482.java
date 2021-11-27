package baekjoon;

import java.io.*;

public class ColorWheel_2482 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        if (k == 1) {
            System.out.println(n);
            System.exit(0);
        }

        // dp[i][j] = 직선에서 i개 중 인접하지 않은 k개를 뽑는 경우의 수

        int[][] dp = new int[n + 1][n + 1];

        // k <= n
        // nCk = (n-1)Ck + (n-1)C(k-1)
        // nC0 = 1
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j == 1) {
                    dp[i][j] = i;
                } else if (j <= (int) Math.ceil(i / 2.0)) {
                    // 마지막을 포함하지 않고 인접하지 않은 j개를 뽑는 경우의 수 +
                    // 마지막을 포함하고 인접하지 않은 j개를 뽑는 경우의 수(= 마지막, 마지막에서 두번째를 제외하고 인접하지 않은 j-1개를 뽑는 경우의 수)
                    dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % 1_000_000_003;
                }
            }
        }

        // 원에서 n개 중 인접하지 않은 k개를 뽑는 경우의 수
        // = 마지막을 포함하지 않고 인접하지 않은 k개를 뽑는 경우의 수 + 마지막을 포함하고 인접하지 않은 k개를 뽑는 경우의 수
        // = 직선에서 n-1개 중 인접하지 않은 k개를 뽑는 경우의 수 + 직선에서 n-3개 중 인접하지 않은 k-1개를 뽑는 경우의 수
        System.out.println((dp[n - 1][k] + dp[n - 3][k - 1]) % 1_000_000_003);
    }
}
