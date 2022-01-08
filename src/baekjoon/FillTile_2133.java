package baekjoon;

import java.io.*;

public class FillTile_2133 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[31];
        dp[0] = 1;
        dp[2] = 3;

        // dp[i] = dp[2] * dp[i - 2] + 2(추가된 모양) * dp[i - 4] + 2 * dp[i - 6] ...
        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[2] * dp[i - 2];
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += 2 * dp[j];
            }
        }
        System.out.println(dp[n]);
    }
}
