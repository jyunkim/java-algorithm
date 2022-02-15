package baekjoon;

import java.io.*;
import java.util.*;

// 주어진 추로 측정할 수 있는 모든 무게를 구해놓는다 -> 완전탐색 + dp(메모이제이션)
public class EqualArmBalance_2629 {

    private static final int MAX_MARBLE_WEIGHT = 40000;

//    private static boolean[] isKnowable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int plumbNum = Integer.parseInt(br.readLine());

        int[] plumbs = new int[plumbNum + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= plumbNum; i++) {
            plumbs[i] = Integer.parseInt(st.nextToken());
        }

//        isKnowable = new boolean[MAX_MARBLE_WEIGHT + 1];
//        dfs(0, 0);

        // 무게를 저장해가며 탐색해 중복 탐색을 없앰
        // dp[i][j] = i번째 추까지 사용해서 무게가 j인 구슬을 왼쪽에 놨을 때 무게를 측정할 수 있는지 여부
        boolean[][] dp = new boolean[plumbNum + 1][MAX_MARBLE_WEIGHT + 1];
        dp[0][0] = true;

        for (int i = 1; i <= plumbNum; i++) {
            for (int j = 0; j <= MAX_MARBLE_WEIGHT; j++) {
                if (dp[i - 1][j]) {
                    if (j - plumbs[i] <= MAX_MARBLE_WEIGHT) {
                        dp[i][Math.abs(j - plumbs[i])] = true; // i번째 추를 왼쪽에 놓는다
                    }
                    dp[i][j] = true; // i번째 추를 놓지 않는다
                    if (j + plumbs[i] <= MAX_MARBLE_WEIGHT) {
                        dp[i][j + plumbs[i]] = true; // i번째 추를 오른쪽에 놓는다
                    }
                }
            }
        }

        int marbleNum = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < marbleNum; i++) {
            int marble = Integer.parseInt(st.nextToken());
            System.out.print(dp[plumbNum][marble] ? "Y " : "N ");
        }
    }

    // 완전탐색 -> 시간초과
    // depth: 추의 index, sum: 구슬을 왼쪽에 올린다고 했을 때 측정할 수 있는 무게
//    private static void dfs(int depth, int sum) {
//        if (depth == plumbNum) {
//            if (sum > 0) {
//                isKnowable[sum] = true;
//            }
//            return;
//        }
//        dfs(depth + 1, sum - plumbs[depth]); // 추를 왼쪽에 올린다
//        dfs(depth + 1, sum); // 추를 올리지 않는다
//        dfs(depth + 1, sum + plumbs[depth]); // 추를 오른쪽에 올린다
//    }
}
