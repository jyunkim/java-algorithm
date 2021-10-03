package baekjoon;

import java.util.*;
import java.io.*;

// 완전 탐색보다는 부분 문제로 나누어 푸는 것이 더 적합해 보임
public class MergeFiles_11066 {
    
    static int[][] dp;

    // 연속한 파일만 합칠 수 있다!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        for (int _i = 0; _i < t; _i++) {
            int n = Integer.parseInt(br.readLine());
            dp = new int[n + 1][n + 1];
            int[] files = new int[n + 1];
            int[] sum = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                // 누적합 저장
                sum[i] = sum[i - 1] + files[i];
            }

            // dp[i][j] = i번째 파일부터 j번째 파일을 합칠 때 필요한 최소 비용
            // 점화식
            // dp[i][i] = files[i]
            // dp[i][i + 1] = files[i] + files[i + 1]
            // dp[i][i + 2] = min(dp[i][i] + dp[i + 1][i + 2] + files[i + 1] + files[i + 2],
            //                    dp[i][i + 1] + dp[i + 2][i + 2] + files[i] + files[i + 1])
            // dp[i][j] = min(.., dp[i][k] + dp[k + 1][j] + files[i] + .. + files[j], ..)

            // 초기화
            for (int i = 1; i <= n; i++) {
                for (int j = i; j <= n; j++) {
                    if (i == j) {
                        dp[i][j] = files[i];
                    } else if (i + 1 == j) {
                        dp[i][j] = files[i] + files[j];
                    } else {
                        dp[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            // 합칠 파일 수를 3부터 n까지 늘려감
            // i: 시작 지점, j: 끝 지점, k: 나눌 지점
            for (int size = 3; size <= n; size++) {
                for (int i = 1; i + size - 1 <= n; i++) {
                    int j = i + size - 1;
                    for (int k = i; k < j; k++) {
                        // 중간 임시 파일 크기도 더해줘야 함
                        int temp = 0;
                        if (i != k) temp += sum[k] - sum[i - 1];
                        if (k + 1 != j) temp += sum[j] - sum[k];
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + temp);
                    }
                }
            }

            System.out.println(dp[1][n]);
        }
    }
}
