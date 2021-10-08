package programmers;

import java.util.*;

public class EatGround {

    int solution(int[][] land) {
        int n = land.length;
        int[][] dp = new int[n + 1][4];

        // 점수를 누적해서 계산
        for (int i = 1; i <= n; i++) {
            dp[i][0] = land[i - 1][0] + Collections.max(Arrays.asList(dp[i - 1][1], dp[i - 1][2], dp[i - 1][3]));
            dp[i][1] = land[i - 1][1] + Collections.max(Arrays.asList(dp[i - 1][0], dp[i - 1][2], dp[i - 1][3]));
            dp[i][2] = land[i - 1][2] + Collections.max(Arrays.asList(dp[i - 1][0], dp[i - 1][1], dp[i - 1][3]));
            dp[i][3] = land[i - 1][3] + Collections.max(Arrays.asList(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2]));
        }

        int answer = 0;
        for (int score : dp[n]) {
            answer = Math.max(answer, score);
        }

        return answer;
    }
}
