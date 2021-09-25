package baekjoon;

import java.io.*;

// 현재의 값이 이전의 값에 영향을 받음 => DP
public class LCS_9251 {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();

        // 해당 인덱스까지의 부분 수열에서의 LCS 길이
        // 0: 초기값(0), 1 ~ 수열길이: 수열의 각 단어
        dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // 부분 수열의 마지막 문자가 같다면 두 수열의 마지막 문자가 모두 없을 때의 LCS의 길이 + 1
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { // 다르다면 두 수열 중 한 수열의 마지막 문자가 없을 때의 LCS의 길이 중 큰 값
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        System.out.println(dp[a.length()][b.length()]);
    }
}
