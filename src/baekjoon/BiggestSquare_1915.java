package baekjoon;

import java.util.*;
import java.io.*;

public class BiggestSquare_1915 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String temp = br.readLine();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Character.getNumericValue(temp.charAt(j - 1));
            }
        }

        // 부분으로 계속 쌓아감
        // dp[i][j]: 해당 인덱스를 오른쪽 하단 꼭짓점으로 하는 가장 큰 정사각형 변의 길이
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) { // 초기값 설정
                    dp[i][j] = arr[i][j];
                } else {
                    if (arr[i][j] == 1) { // 해당 칸이 1인 경우에만 왼쪽, 위, 왼쪽 위 대각선 중 가장 작은 값 + 1
                        dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    }
                }
                answer = Math.max(answer, dp[i][j]);
            }
        }

        System.out.println((int) Math.pow(answer, 2));
    }
}
