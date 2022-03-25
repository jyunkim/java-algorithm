package baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

// 완전탐색
public class WorldCup_6987 {

    // 1이면 가능, 0이면 불가능
    private static int isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int[][] result = new int[6][3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean breakFlag = false;
            isPossible = 0;

            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    result[j][k] = Integer.parseInt(st.nextToken());
                }
                // 햔 팀이 5번보다 많은 경기를 할 경우 불가능한 결과
                if (IntStream.of(result[j]).sum() > 5) {
                    breakFlag = true;
                    break;
                }
            }

            if (!breakFlag) {
                match(result, 0, 0, 1);
            }
            sb.append(isPossible);
            if (i < 3) {
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }

    // home팀을 기준으로 모든 away팀과 한번씩 경기 진행
    private static void match(int[][] result, int count, int home, int away) {
        // 총 15번의 경기를 완료했으면 가능한 결과
        if (count == 15) {
            isPossible = 1;
            return;
        }
        // 모든 away팀과 경기를 완료했으면 home팀을 다음 팀으로 변경
        if (away == 6) {
            // 마지막 경기(4-5)까지 진행했는데 15번의 경기를 못 치뤘을 경우 불가능한 결과
            if (home == 4) {
                return;
            }
            match(result, count, home + 1, home + 2);
        }

        // 주어진 결과로 가능한 범위 내에서 경기 진행(승/무/패)
        for (int i = 0; i < 3; i++) {
            if (result[home][i] > 0 && result[away][2 - i] > 0) {
                result[home][i]--;
                result[away][2 - i]--;
                match(result, count + 1, home, away + 1);
                result[home][i]++;
                result[away][2 - i]++;
            }
        }
    }
}
