package baekjoon;

import java.io.*;
import java.util.*;

public class Dictionary_1256 {

    private static final int MAX = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // dp[i][j] = iCj
        long[][] dp = new long[n + m + 1][m + 1];
        for (int i = 0; i <= n + m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n + m; i++) {
            for (int j = 1; j <= m; j++) {
                // nCr = n-1Cr + n-1Cr-1
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                if (dp[i][j] > MAX) {
                    dp[i][j] = MAX + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int length = n + m;

        if (k > dp[length][m]) {
            System.out.println(-1);
            return;
        }

        while (length > 0) {
            long CountStartWithA = dp[length - 1][m];
            if (k <= CountStartWithA) { // dp[length-1][m] = 맨 앞 글자가 a인 문자열 수
                sb.append('a');
                n--;
            } else {
                sb.append('z');
                m--;
                k -= CountStartWithA;
            }
            length = n + m;
        }

        System.out.println(sb);
    }
}
