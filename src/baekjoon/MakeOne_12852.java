package baekjoon;

import java.io.*;
import java.util.*;

public class MakeOne_12852 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        Map<Integer, Integer> sequence = new HashMap<>();

        // dp[i] = i에서 1로 만드는데 필요한 최소 연산 횟수
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            int index = 1;
            // 현재 필요한 연산 횟수가 더 큰 경우에만 갱신
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                index = i / 3;
            }
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                index = i / 2;
            }
            if (dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
                index = i - 1;
            }
            // 경로 저장 - (현재 index, 이전 index)
            sequence.putIfAbsent(i, index);
            sequence.replace(i, index);
        }

        System.out.println(dp[n]);

        int from = n;
        StringBuilder sb = new StringBuilder(Integer.toString(n));
        while (from != 1) {
            int to = sequence.get(from);
            sb.append(" ").append(to);
            from = to;
        }
        System.out.println(sb);
    }
}
