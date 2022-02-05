package baekjoon;

import java.io.*;
import java.util.*;

// 완전 탐색 -> 시간 초과
// DP - 부분 문제, 이전 값 기억
public class Coin_2293 {

//    private static final List<Integer> prices = new ArrayList<>();
//    private static int k;
//    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

//        for (int i = 0; i < n; i++) {
//            int price = Integer.parseInt(br.readLine());
//            if (price <= k) {
//                prices.add(price);
//            }
//        }
//        dfs(0, 0);
//        System.out.println(answer);

        // dp[i][j] = i번째 동전까지 이용해서 j원을 만드는 경우의 수
        // dp[i][j] = dp[i-1][j] + dp[i][j-prices[i]]
        //          = i-1번째 동전까지 이용해서 j원을 만드는 경우의 수 + i번째 동전을 적어도 하나 포함하여 j원을 만드는 경우의 수
        //          = i-1번째 동전까지 이용해서 j원을 만드는 경우의 수 + i번째 동전까지 이용해서 j-prices[i]원을 만드는 경우의 수
        int[][] dp = new int[n + 1][k + 1];
        int[] prices = new int[n + 1];

        // 초기화
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            prices[i] = Integer.parseInt(br.readLine());
            for (int j = 1; j <= k; j++) {
                if (j >= prices[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - prices[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[n][k]);
    }

//    private static void dfs(int index, int sum) {
//        if (sum >= k) {
//            if (sum == k) {
//                answer++;
//            }
//            return;
//        }
//
//        for (int i = index; i < prices.size(); i++) {
//            dfs(i, sum + prices.get(i));
//        }
//    }
}
