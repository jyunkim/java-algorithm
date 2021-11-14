package baekjoon;

import java.io.*;

public class CrossStoneBridge_2602 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String magicString = br.readLine();
        String devilBridge = br.readLine();
        String angelBridge = br.readLine();

        int n = devilBridge.length();
        int l = magicString.length();

        // dp[i][j][k]: i번째 다리의 j번째 돌까지 두루마리 문자열의 k번째 문자까지 포함하여 건너갈 수 있는 경우의 수
        int[][][] dp = new int[2][n + 1][l + 1];

        // 두루마리 문자열의 첫번째 문자의 경우 돌다리의 돌과 일치하면 dp[0,1][j][1]의 값을 1씩 증가시켜줘야 하기 때문에 dp[1,0][j-1][0]을 1로 만들어서 점화식에서 1씩 더해지게 함
        for (int i = 1; i <= n; i++) {
            if (devilBridge.charAt(i - 1) == magicString.charAt(0)) {
                dp[1][i - 1][0] = 1;
            }
            if (angelBridge.charAt(i - 1) == magicString.charAt(0)) {
                dp[0][i - 1][0] = 1;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= l; j++) {
                // 악마 돌다리의 i번째 돌의 적힌 문자와 두루마리 문자열의 j번째 문자가 같을 경우
                if (devilBridge.charAt(i - 1) == magicString.charAt(j - 1)) {
                    // dp[0][i][j] = 악마 돌다리의 이전 돌까지의 경우의 수 + 천사 돌다리의 i-1번째 돌까지의 두루마리 문자열의 j-1번째 문자까지 포함하여 건너갈 수 있는 경우의 수
                    dp[0][i][j] = dp[0][i - 1][j] + dp[1][i - 1][j - 1];
                } else {
                    dp[0][i][j] = dp[0][i - 1][j];
                }
                // 천사 돌다리
                if (angelBridge.charAt(i - 1) == magicString.charAt(j - 1)) {
                    dp[1][i][j] = dp[1][i - 1][j] + dp[0][i - 1][j - 1];
                } else {
                    dp[1][i][j] = dp[1][i - 1][j];
                }
            }
        }

        System.out.println(dp[0][n][l] + dp[1][n][l]);
    }
}
